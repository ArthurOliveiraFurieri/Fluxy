/* metricas-engajamento.js — gráficos e comportamentos da página de Métricas */

document.addEventListener('DOMContentLoaded', function () {

    Chart.defaults.font.family = "'Segoe UI', system-ui, sans-serif";
    Chart.defaults.font.size   = 10;
    Chart.defaults.color       = '#9ca3af';

    /* ── Dados ──────────────────────────────────────────── */

    const horasData = [
        {h:'06h',v:800 },{h:'07h',v:1200},{h:'08h',v:1900},{h:'09h',v:2600},
        {h:'10h',v:3400,peak:true},{h:'11h',v:3600,peak:true},{h:'12h',v:2800},
        {h:'13h',v:3200,peak:true},{h:'14h',v:3300,peak:true},{h:'15h',v:2900},
        {h:'16h',v:2500},{h:'17h',v:2200},{h:'18h',v:2000},{h:'19h',v:1700},
        {h:'20h',v:1400},{h:'21h',v:900}
    ];

    const conteudoData = [
        { label: 'Ferramentas',   pct: 81, color: '#22c55e' },
        { label: 'Consultorias',  pct: 76, color: '#6366f1' },
        { label: 'Cursos Online', pct: 72, color: '#6366f1' },
        { label: 'Eventos',       pct: 68, color: '#f59e0b' },
        { label: 'Artigos/Blog',  pct: 54, color: '#ef4444' },
    ];

    const retencaoData = [
        { label: 'Consolidados',   color: '#f59e0b', data: [100, 68, 54, 44] },
        { label: 'MEI',            color: '#6366f1', data: [100, 72, 58, 46] },
        { label: 'Estudantes',     color: '#22c55e', data: [100, 60, 44, 34] },
        { label: 'Empreendedores', color: '#a78bfa', data: [100, 65, 50, 40] },
    ];

    const segmentosData = [
        { nome: 'Empreendedores',    cor: '#6366f1', pct: '38%', engaj: '71%', retorno: '45%', sessoes: '54.150' },
        { nome: 'MEI',               cor: '#22c55e', pct: '27%', engaj: '74%', retorno: '52%', sessoes: '38.475' },
        { nome: 'Estudantes',        cor: '#a78bfa', pct: '18%', engaj: '48%', retorno: '22%', sessoes: '7.125'  },
        { nome: 'Emp. Consolidados', cor: '#f59e0b', pct: '12%', engaj: '62%', retorno: '33%', sessoes: '25.650' },
        { nome: 'Iniciantes',        cor: '#3b82f6', pct: '79%', engaj: '79%', retorno: '58%', sessoes: '17.100' },
        { nome: 'Outros',            cor: '#9ca3af', pct: '5%',  engaj: '48%', retorno: '22%', sessoes: '7.125'  },
    ];

    const radarData = [
        { label: 'Consolidados',   color: '#f59e0b', vals: [82, 70, 75, 65, 80, 60] },
        { label: 'MEI',            color: '#6366f1', vals: [70, 78, 68, 72, 65, 82] },
        { label: 'Estudantes',     color: '#22c55e', vals: [60, 55, 78, 50, 68, 72] },
        { label: 'Empreendedores', color: '#a78bfa', vals: [75, 68, 65, 80, 58, 68] },
    ];

    /* ── 1. Bar Chart ───────────────────────────────────── */
    const yAxis    = document.getElementById('yAxis');
    const barsArea = document.getElementById('barsArea');

    if (yAxis) {
        yAxis.innerHTML = ['3600','2700','1800','900','0']
            .map(l => `<span>${l}</span>`).join('');
    }

    if (barsArea) {
        horasData.forEach(d => {
            const pct = Math.round((d.v / 3600) * 100);
            const col = document.createElement('div');
            col.className = 'bar-col';
            col.innerHTML = `
                <div class="bar${d.peak ? ' bar--peak' : ''}" style="height:${pct}%"></div>
                <span class="bar-label">${d.h}</span>
            `;
            barsArea.appendChild(col);
        });
    }

    /* ── 2. Progress Bars ───────────────────────────────── */
    const progList = document.getElementById('progList');
    if (progList) {
        progList.innerHTML = conteudoData.map(c => `
            <div class="prog-row">
                <span class="prog-label">${c.label}</span>
                <div class="prog-track">
                    <div class="prog-fill" style="width:${c.pct}%;background:${c.color}"></div>
                </div>
                <span class="prog-pct" style="color:${c.color}">${c.pct}%</span>
            </div>
        `).join('');
    }

    /* ── 3. Line Chart — Retenção ───────────────────────── */
    new Chart(document.getElementById('chartRetencao'), {
        type: 'line',
        data: {
            labels: ['Semana 1', 'Semana 2', 'Semana 3', 'Semana 4'],
            datasets: retencaoData.map(s => ({
                label:                s.label,
                data:                 s.data,
                borderColor:          s.color,
                backgroundColor:      s.color + '14',
                fill:                 false,
                tension:              0.35,
                borderWidth:          2,
                pointRadius:          4,
                pointHoverRadius:     6,
                pointBackgroundColor: s.color,
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

    /* ── 4. Tabela de Segmentos ─────────────────────────── */
    const segTableBody = document.getElementById('segTableBody');
    if (segTableBody) {
        segTableBody.innerHTML = segmentosData.map(s => `
            <tr>
                <td>
                    <div class="seg-name">
                        <div class="seg-dot" style="background:${s.cor}"></div>
                        ${s.nome}
                        <span class="seg-pct">${s.pct}</span>
                    </div>
                </td>
                <td class="td-c">${s.engaj}</td>
                <td class="td-c">${s.retorno}</td>
                <td class="td-s">${s.sessoes}</td>
            </tr>
        `).join('');
    }

    /* ── 5. Radar ───────────────────────────────────────── */
    new Chart(document.getElementById('chartRadar'), {
        type: 'radar',
        data: {
            labels: ['Engajamento', 'Retenção', 'Conteúdo', 'Conversão', 'Satisfação', 'Tempo'],
            datasets: radarData.map(d => ({
                label:                d.label,
                data:                 d.vals,
                borderColor:          d.color,
                backgroundColor:      d.color + '28',
                borderWidth:          2,
                pointRadius:          3,
                pointBackgroundColor: d.color,
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

    /* Legenda do radar */
    const radarLegend = document.getElementById('radarLegend');
    if (radarLegend) {
        radarLegend.innerHTML = radarData.map(d => `
            <div class="chart-legend__item">
                <div class="chart-legend__dot" style="background:${d.color}"></div>
                ${d.label}
            </div>
        `).join('');
    }

});