/* metricas-engajamento.js — dados vindos da API + filtros funcionais */

document.addEventListener('DOMContentLoaded', function () {

    Chart.defaults.font.family = "'Segoe UI', system-ui, sans-serif";
    Chart.defaults.font.size   = 10;
    Chart.defaults.color       = '#9ca3af';

    /* ── Estado dos filtros ──────────────────────────── */
    const filtros = {
        periodo:  7,
        segmento: 'todos',
        fonte:    'todas',
        tipo:     'todos',
    };

    const periodoOpcoes = [
        { valor: 7,  label: 'Últimos 7 dias'  },
        { valor: 14, label: 'Últimos 14 dias' },
        { valor: 30, label: 'Últimos 30 dias' },
    ];

    const fonteOpcoes = [
        { valor: 'todas',    label: 'Todas as fontes', mult: 1.00 },
        { valor: 'organico', label: 'Orgânico',        mult: 1.10 },
        { valor: 'social',   label: 'Social',          mult: 0.85 },
        { valor: 'direto',   label: 'Direto',          mult: 0.95 },
    ];

    /* ── Cache dos dados brutos ──────────────────────── */
    let cacheHoras     = null;
    let cacheConteudo  = null;
    let cacheRetencao  = null;
    let cacheSegmentos = null;
    let cacheRadar     = null;

    let chartRetencao = null;
    let chartRadar    = null;

    /* ── Dropdowns ───────────────────────────────────── */
    function criarDropdown(anchorId, opcoes, onSelect) {
        const anchor = document.getElementById(anchorId);
        if (!anchor) return;

        anchor.addEventListener('click', e => {
            e.stopPropagation();
            fecharTodosDropdowns();

            const menu = document.createElement('div');
            menu.className = 'filtro-dropdown';

            opcoes.forEach(op => {
                const item = document.createElement('div');
                item.className = 'filtro-dropdown__item' + (op.ativo ? ' ativo' : '');
                item.textContent = op.label;
                item.addEventListener('click', () => {
                    onSelect(op);
                    anchor.querySelector('span').textContent = op.label;
                    fecharTodosDropdowns();
                });
                menu.appendChild(item);
            });

            const rect = anchor.getBoundingClientRect();
            menu.style.top  = (rect.bottom + window.scrollY + 4) + 'px';
            menu.style.left = rect.left + 'px';
            document.body.appendChild(menu);
        });
    }

    function fecharTodosDropdowns() {
        document.querySelectorAll('.filtro-dropdown').forEach(d => d.remove());
    }

    document.addEventListener('click', fecharTodosDropdowns);

    /* ── Inicializa filtros ───────────────────────────── */
    criarDropdown('filterPeriodo', periodoOpcoes, op => {
        filtros.periodo = op.valor;
        renderHoras();
        renderSegmentos();
    });

    const segmentoOpcoes = () => [
        { valor: 'todos', label: 'Todos os usuários' },
        ...(cacheSegmentos || []).map(s => ({ valor: s.nome, label: s.nome })),
    ];

    document.getElementById('filterUsuarios')?.addEventListener('click', e => {
        e.stopPropagation();
        fecharTodosDropdowns();

        const anchor = document.getElementById('filterUsuarios');
        const menu   = document.createElement('div');
        menu.className = 'filtro-dropdown';

        segmentoOpcoes().forEach(op => {
            const item = document.createElement('div');
            item.className = 'filtro-dropdown__item';
            item.textContent = op.label;
            item.addEventListener('click', () => {
                filtros.segmento = op.valor;
                anchor.querySelector('span').textContent = op.label;
                fecharTodosDropdowns();
                renderRetencao();
                renderSegmentos();
                renderRadar();
            });
            menu.appendChild(item);
        });

        const rect = anchor.getBoundingClientRect();
        menu.style.top  = (rect.bottom + window.scrollY + 4) + 'px';
        menu.style.left = rect.left + 'px';
        document.body.appendChild(menu);
    });

    criarDropdown('filterFontes', fonteOpcoes, op => {
        filtros.fonte = op.valor;
        renderConteudo();
        renderHoras();
    });

    document.getElementById('filterTipos')?.addEventListener('click', e => {
        e.stopPropagation();
        fecharTodosDropdowns();

        const anchor  = document.getElementById('filterTipos');
        const opcoes  = [
            { valor: 'todos', label: 'Todos os tipos' },
            ...(cacheConteudo || []).map(c => ({ valor: c.label, label: c.label })),
        ];

        const menu = document.createElement('div');
        menu.className = 'filtro-dropdown';

        opcoes.forEach(op => {
            const item = document.createElement('div');
            item.className = 'filtro-dropdown__item';
            item.textContent = op.label;
            item.addEventListener('click', () => {
                filtros.tipo = op.valor;
                anchor.querySelector('span').textContent = op.label;
                fecharTodosDropdowns();
                renderConteudo();
            });
            menu.appendChild(item);
        });

        const rect = anchor.getBoundingClientRect();
        menu.style.top  = (rect.bottom + window.scrollY + 4) + 'px';
        menu.style.left = rect.left + 'px';
        document.body.appendChild(menu);
    });

    /* ── Botão refresh ───────────────────────────────── */
    document.getElementById('btnRefresh')?.addEventListener('click', () => {
        const btn = document.getElementById('btnRefresh');
        btn.classList.add('spinning');
        carregarTudo().finally(() => btn.classList.remove('spinning'));
    });

    /* ── Carga inicial ───────────────────────────────── */
    carregarTudo();

    /* ── Orquestrador ────────────────────────────────── */
    async function carregarTudo() {
        const [horas, conteudo, retencao, segmentos, radar] = await Promise.all([
            fetchJson('/api/metricas/horas'),
            fetchJson('/api/metricas/conteudo'),
            fetchJson('/api/metricas/retencao'),
            fetchJson('/api/metricas/segmentos'),
            fetchJson('/api/metricas/radar'),
        ]);

        cacheHoras     = horas;
        cacheConteudo  = conteudo;
        cacheRetencao  = retencao;
        cacheSegmentos = segmentos;
        cacheRadar     = radar;

        renderHoras();
        renderConteudo();
        renderRetencao();
        renderSegmentos();
        renderRadar();
    }

    /* ── Multiplicador de fonte ──────────────────────── */
    function multFonte() {
        return fonteOpcoes.find(f => f.valor === filtros.fonte)?.mult ?? 1;
    }

    /* ── Multiplicador de período ────────────────────── */
    function multPeriodo() {
        const tabela = { 7: 1, 14: 1.9, 30: 3.8 };
        return tabela[filtros.periodo] ?? 1;
    }

    /* ── 1. Bar Chart — Horas ────────────────────────── */
    function renderHoras() {
        if (!cacheHoras) return;
        const mult = multPeriodo() * multFonte();
        const horas = cacheHoras.map(h => ({ ...h, valor: Math.round(h.valor * mult) }));

        const maxValor = Math.max(...horas.map(h => h.valor));
        const topo     = Math.ceil(maxValor / 900) * 900;

        const yAxis = document.getElementById('yAxis');
        if (yAxis) {
            const steps = [topo, topo * 0.75, topo * 0.5, topo * 0.25, 0];
            yAxis.innerHTML = steps.map(v => `<span>${Math.round(v).toLocaleString('pt-BR')}</span>`).join('');
        }

        const barsArea = document.getElementById('barsArea');
        if (barsArea) {
            barsArea.innerHTML = '';
            horas.forEach(h => {
                const pct = Math.round((h.valor / topo) * 100);
                const col = document.createElement('div');
                col.className = 'bar-col';
                col.title = `${h.hora}: ${h.valor.toLocaleString('pt-BR')} sessões`;
                col.innerHTML = `
                    <div class="bar${h.peak ? ' bar--peak' : ''}" style="height:${pct}%"></div>
                    <span class="bar-label">${h.hora}</span>
                `;
                barsArea.appendChild(col);
            });
        }
    }

    /* ── 2. Progress Bars — Conteúdo ─────────────────── */
    function renderConteudo() {
        if (!cacheConteudo) return;
        const mult = multFonte();

        let dados = cacheConteudo;
        if (filtros.tipo !== 'todos') {
            dados = dados.filter(c => c.label === filtros.tipo);
        }

        const progList = document.getElementById('progList');
        if (progList) {
            if (dados.length === 0) {
                progList.innerHTML = '<p style="color:#9ca3af;font-size:13px;padding:8px 0">Nenhum resultado para este filtro.</p>';
                return;
            }
            progList.innerHTML = dados.map(c => {
                const pct = Math.min(99, Math.round(c.percentual * mult));
                return `
                    <div class="prog-row">
                        <span class="prog-label">${c.label}</span>
                        <div class="prog-track">
                            <div class="prog-fill" style="width:${pct}%;background:${c.cor}"></div>
                        </div>
                        <span class="prog-pct" style="color:${c.cor}">${pct}%</span>
                    </div>
                `;
            }).join('');
        }
    }

    /* ── 3. Line Chart — Retenção ────────────────────── */
    function renderRetencao() {
        if (!cacheRetencao) return;

        let dados = cacheRetencao;
        if (filtros.segmento !== 'todos') {
            dados = dados.filter(r => r.label === filtros.segmento);
        }

        const datasets = dados.map(r => ({
            label:                r.label,
            data:                 [r.semana1, r.semana2, r.semana3, r.semana4],
            borderColor:          r.cor,
            backgroundColor:      r.cor + '14',
            fill:                 false,
            tension:              0.35,
            borderWidth:          2,
            pointRadius:          4,
            pointHoverRadius:     6,
            pointBackgroundColor: r.cor,
            pointBorderColor:     '#fff',
            pointBorderWidth:     1.5,
        }));

        const canvas = document.getElementById('chartRetencao');
        if (!canvas) return;

        if (chartRetencao) {
            chartRetencao.data.datasets = datasets;
            chartRetencao.update('active');
        } else {
            chartRetencao = new Chart(canvas, {
                type: 'line',
                data: {
                    labels: ['Semana 1', 'Semana 2', 'Semana 3', 'Semana 4'],
                    datasets,
                },
                options: {
                    plugins: {
                        legend: { display: false },
                        tooltip: { mode: 'index', intersect: false },
                    },
                    scales: {
                        x: { grid: { display: false }, border: { display: false } },
                        y: {
                            min: 0, max: 100,
                            grid: { color: '#f3f4f6' },
                            border: { display: false },
                            ticks: { callback: v => v + '%', maxTicksLimit: 5 },
                        },
                    },
                },
            });
        }
    }

    /* ── 4. Tabela de Segmentos ──────────────────────── */
    function renderSegmentos() {
        if (!cacheSegmentos) return;
        const multP = multPeriodo();

        let dados = cacheSegmentos;
        if (filtros.segmento !== 'todos') {
            dados = dados.filter(s => s.nome === filtros.segmento);
        }

        const tbody = document.getElementById('segTableBody');
        if (tbody) {
            if (dados.length === 0) {
                tbody.innerHTML = '<tr><td colspan="4" style="color:#9ca3af;font-size:13px;text-align:center;padding:12px">Nenhum resultado.</td></tr>';
                return;
            }
            tbody.innerHTML = dados.map(s => `
                <tr>
                    <td>
                        <div class="seg-name">
                            <div class="seg-dot" style="background:${s.cor}"></div>
                            ${s.nome}
                            <span class="seg-pct">${s.pct}%</span>
                        </div>
                    </td>
                    <td class="td-c">${s.engajamento}%</td>
                    <td class="td-c">${s.retorno}%</td>
                    <td class="td-s">${Math.round(s.sessoes * multP).toLocaleString('pt-BR')}</td>
                </tr>
            `).join('');
        }
    }

    /* ── 5. Radar ────────────────────────────────────── */
    function renderRadar() {
        if (!cacheRadar) return;

        let dados = cacheRadar;
        if (filtros.segmento !== 'todos') {
            dados = dados.filter(d => d.label === filtros.segmento);
        }

        const datasets = dados.map(d => ({
            label:                d.label,
            data:                 [d.val1, d.val2, d.val3, d.val4, d.val5, d.val6],
            borderColor:          d.cor,
            backgroundColor:      d.cor + '28',
            borderWidth:          2,
            pointRadius:          3,
            pointBackgroundColor: d.cor,
            pointBorderColor:     '#fff',
            pointBorderWidth:     1.5,
        }));

        const canvas = document.getElementById('chartRadar');
        if (!canvas) return;

        if (chartRadar) {
            chartRadar.data.datasets = datasets;
            chartRadar.update('active');
        } else {
            chartRadar = new Chart(canvas, {
                type: 'radar',
                data: {
                    labels: ['Engajamento', 'Retenção', 'Conteúdo', 'Conversão', 'Satisfação', 'Tempo'],
                    datasets,
                },
                options: {
                    plugins: {
                        legend: { display: false },
                        tooltip: { callbacks: { label: c => ` ${c.dataset.label}: ${c.parsed.r}` } },
                    },
                    scales: {
                        r: {
                            min: 0, max: 100,
                            ticks: { stepSize: 25, backdropColor: 'transparent' },
                            grid:  { color: '#e5e7eb' },
                            pointLabels: { font: { size: 11 }, color: '#6b7280' },
                        },
                    },
                },
            });
        }

        const radarLegend = document.getElementById('radarLegend');
        if (radarLegend) {
            radarLegend.innerHTML = dados.map(d => `
                <div class="chart-legend__item">
                    <div class="chart-legend__dot" style="background:${d.cor}"></div>
                    ${d.label}
                </div>
            `).join('');
        }
    }

    /* ── Helper ──────────────────────────────────────── */
    async function fetchJson(url) {
        try {
            const res = await fetch(url);
            if (!res.ok) throw new Error(`HTTP ${res.status}`);
            return await res.json();
        } catch (e) {
            console.error('Erro ao buscar', url, e);
            return null;
        }
    }
});
