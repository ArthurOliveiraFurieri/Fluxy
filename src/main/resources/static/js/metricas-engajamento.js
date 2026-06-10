/* metricas-engajamento.js — dados via backend (opção A) */

document.addEventListener('DOMContentLoaded', function () {

    Chart.defaults.font.family = "'Segoe UI', system-ui, sans-serif";
    Chart.defaults.font.size   = 10;
    Chart.defaults.color       = '#9ca3af';

    carregarTudo();

    // Botão de atualizar regenera os dados no backend
    const btnRefresh = document.getElementById('btnRefresh');
    if (btnRefresh) {
        btnRefresh.addEventListener('click', function () {
            fetch('/api/regenerar', { method: 'POST' })
                .then(() => location.reload())
                .catch(err => console.error('Erro ao regenerar:', err));
        });
    }
});

function carregarTudo() {
    Promise.all([
        fetch('/api/metricas/horas').then(r => r.json()),
        fetch('/api/metricas/conteudo').then(r => r.json()),
        fetch('/api/metricas/retencao').then(r => r.json()),
        fetch('/api/metricas/radar').then(r => r.json()),
        fetch('/api/metricas/segmentos').then(r => r.json()),
    ])
    .then(([horas, conteudo, retencao, radar, segmentos]) => {
        renderBarChart(horas);
        renderProgress(conteudo);
        renderRetencao(retencao);
        renderSegTable(segmentos);
        renderRadar(radar);
    })
    .catch(err => console.error('Erro ao carregar métricas:', err));
}

/* ── 1. Bar Chart — Horário ─────────────────────── */
function renderBarChart(horas) {
    const yAxis    = document.getElementById('yAxis');
    const barsArea = document.getElementById('barsArea');

    const maxVal = Math.max(...horas.map(h => h.valor));
    const topo   = Math.ceil(maxVal / 900) * 900;

    if (yAxis) {
        const labels = [];
        for (let i = 4; i >= 0; i--) labels.push(Math.round(topo * i / 4).toString());
        yAxis.innerHTML = labels.map(l => `<span>${l}</span>`).join('');
    }

    if (barsArea) {
        barsArea.innerHTML = '';
        horas.forEach(d => {
            const pct = Math.round((d.valor / topo) * 100);
            const col = document.createElement('div');
            col.className = 'bar-col';
            col.innerHTML = `
                <div class="bar${d.peak ? ' bar--peak' : ''}" style="height:${pct}%"></div>
                <span class="bar-label">${d.hora}</span>
            `;
            barsArea.appendChild(col);
        });
    }
}

/* ── 2. Progress Bars — Conteúdo ────────────────── */
function renderProgress(conteudo) {
    const progList = document.getElementById('progList');
    if (!progList) return;

    progList.innerHTML = conteudo.map(c => `
        <div class="prog-row">
            <span class="prog-label">${c.label}</span>
            <div class="prog-track">
                <div class="prog-fill" style="width:${c.percentual}%;background:${c.cor}"></div>
            </div>
            <span class="prog-pct" style="color:${c.cor}">${c.percentual}%</span>
        </div>
    `).join('');
}

/* ── 3. Line Chart — Retenção ───────────────────── */
let chartRetencaoInstance = null;
function renderRetencao(retencao) {
    const ctx = document.getElementById('chartRetencao');
    if (!ctx) return;

    if (chartRetencaoInstance) chartRetencaoInstance.destroy();

    chartRetencaoInstance = new Chart(ctx, {
        type: 'line',
        data: {
            labels: ['Semana 1', 'Semana 2', 'Semana 3', 'Semana 4'],
            datasets: retencao.map(s => ({
                label:                s.label,
                data:                 [s.semana1, s.semana2, s.semana3, s.semana4],
                borderColor:          s.cor,
                backgroundColor:      s.cor + '14',
                fill:                 false,
                tension:              0.35,
                borderWidth:          2,
                pointRadius:          4,
                pointHoverRadius:     6,
                pointBackgroundColor: s.cor,
                pointBorderColor:     '#fff',
                pointBorderWidth:     1.5,
            })),
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

/* ── 4. Tabela de Segmentos ─────────────────────── */
function renderSegTable(segmentos) {
    const segTableBody = document.getElementById('segTableBody');
    if (!segTableBody) return;

    segTableBody.innerHTML = segmentos.map(s => `
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
            <td class="td-s">${formatarSessoes(s.sessoes)}</td>
        </tr>
    `).join('');
}

/* ── 5. Radar ───────────────────────────────────── */
let chartRadarInstance = null;
function renderRadar(radar) {
    const ctx = document.getElementById('chartRadar');
    if (!ctx) return;

    if (chartRadarInstance) chartRadarInstance.destroy();

    chartRadarInstance = new Chart(ctx, {
        type: 'radar',
        data: {
            labels: ['Engajamento', 'Retenção', 'Conteúdo', 'Conversão', 'Satisfação', 'Tempo'],
            datasets: radar.map(d => ({
                label:                d.label,
                data:                 [d.val1, d.val2, d.val3, d.val4, d.val5, d.val6],
                borderColor:          d.cor,
                backgroundColor:      d.cor + '28',
                borderWidth:          2,
                pointRadius:          3,
                pointBackgroundColor: d.cor,
                pointBorderColor:     '#fff',
                pointBorderWidth:     1.5,
            })),
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

    const radarLegend = document.getElementById('radarLegend');
    if (radarLegend) {
        radarLegend.innerHTML = radar.map(d => `
            <div class="chart-legend__item">
                <div class="chart-legend__dot" style="background:${d.cor}"></div>
                ${d.label}
            </div>
        `).join('');
    }
}

/* ── Utilitário ─────────────────────────────────── */
function formatarSessoes(n) {
    return n.toLocaleString('pt-BR');
}