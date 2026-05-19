/* visao-geral.js — gráficos e comportamentos da Visão Geral */

document.addEventListener('DOMContentLoaded', function () {

    Chart.defaults.font.family = "'Segoe UI', system-ui, sans-serif";
    Chart.defaults.font.size   = 10;
    Chart.defaults.color       = '#9ca3af';

    /* ── Dados ──────────────────────────────────────────── */
    const labels = Array.from({ length: 26 }, (_, i) => {
        const d = new Date(2024, 3, 1 + i);
        return (d.getDate() === 1 || d.getDate() % 5 === 1)
            ? `${String(d.getDate()).padStart(2, '0')}/04` : '';
    });

    const engajamento = [65,72,60,68,63,70,67,74,61,66,64,71,68,75,62,69,65,73,60,68,66,72,64,70,67,76];
    const retorno     = [38,42,36,40,37,44,39,43,35,41,38,45,40,42,36,43,39,44,37,40,38,43,36,41,39,45];
    const volume      = [4200,3800,4500,3200,1800,2400,4100,3600,4800,3000,1900,2600,4300,3700,4600,3100,1700,2500,4000,3500,4700,3200,1800,2400,4400,4900];

    /* ── Opções comuns ──────────────────────────────────── */
    const commonOptions = {
        maintainAspectRatio: false,
        plugins: {
            legend: { display: false },
            tooltip: { mode: 'index', intersect: false },
        },
        scales: {
            x: { grid: { display: false }, border: { display: false } },
            y: { grid: { color: '#f3f4f6' }, border: { display: false }, ticks: { maxTicksLimit: 5 } },
        },
        interaction: { mode: 'nearest', axis: 'x', intersect: false },
    };

    /* ── Gráfico: Evolução Temporal ─────────────────────── */
    new Chart(document.getElementById('chartEvolucao'), {
        type: 'line',
        data: {
            labels,
            datasets: [
                {
                    data: engajamento,
                    borderColor: '#3b82f6',
                    backgroundColor: 'rgba(59,130,246,.08)',
                    fill: true, tension: .4, borderWidth: 2,
                    pointRadius: 0, pointHoverRadius: 4,
                },
                {
                    data: retorno,
                    borderColor: '#10b981',
                    backgroundColor: 'rgba(16,185,129,.05)',
                    fill: true, tension: .4, borderWidth: 2,
                    pointRadius: 0, pointHoverRadius: 4,
                },
            ],
        },
        options: commonOptions,
    });

    /* ── Gráfico: Volume de Sessões ─────────────────────── */
    new Chart(document.getElementById('chartVolume'), {
        type: 'line',
        data: {
            labels,
            datasets: [{
                data: volume,
                borderColor: '#7c3aed',
                backgroundColor: 'rgba(124,58,237,.06)',
                fill: true, tension: .4, borderWidth: 2,
                pointRadius: 0, pointHoverRadius: 4,
            }],
        },
        options: commonOptions,
    });

    /* ── Gráfico: Fontes de Tráfego (Donut) ─────────────── */
    const fontes = [
        { label: 'Orgânico', pct: 45, color: '#10b981' },
        { label: 'Direto',   pct: 23, color: '#3b82f6' },
        { label: 'Social',   pct: 18, color: '#7c3aed' },
        { label: 'E-mail',   pct:  9, color: '#f59e0b' },
        { label: 'Pago',     pct:  5, color: '#ef4444' },
    ];

    new Chart(document.getElementById('chartDonut'), {
        type: 'doughnut',
        data: {
            labels: fontes.map(f => f.label),
            datasets: [{
                data: fontes.map(f => f.pct),
                backgroundColor: fontes.map(f => f.color),
                borderWidth: 0,
                hoverOffset: 4,
            }],
        },
        options: {
            cutout: '68%',
            plugins: {
                legend: { display: false },
                tooltip: { callbacks: { label: c => ` ${c.label}: ${c.parsed}%` } },
            },
        },
    });

    /* Legenda do donut */
    const donutLegend = document.getElementById('donutLegend');
    if (donutLegend) {
        donutLegend.innerHTML = fontes.map(f => `
            <div class="dl-row">
                <div class="dl-left">
                    <div class="dl-dot" style="background:${f.color}"></div>
                    ${f.label}
                </div>
                <div class="dl-pct">${f.pct}%</div>
            </div>
        `).join('');
    }

    /* ── Funil resumido ─────────────────────────────────── */
    const funil = [
        { label: 'Entrada no',            pct: 100, count: '143k', delta: '',     color: '#2563eb' },
        { label: 'Exploração de',         pct:  69, count: '98k',  delta: '-31%', color: '#3b82f6' },
        { label: 'Engajamento Profundo',  pct:  53, count: '52k',  delta: '-47%', color: '#7c3aed' },
        { label: 'Intenção de',           pct:  44, count: '23k',  delta: '-56%', color: '#f59e0b' },
        { label: 'Conversão /',           pct:  41, count: '9k',   delta: '-59%', color: '#10b981' },
    ];

    const funilRows = document.getElementById('funilRows');
    if (funilRows) {
        funilRows.innerHTML = funil.map(f => `
            <div class="funil-row">
                <div class="funil-row__label">${f.label}</div>
                <div class="funil-row__bar-wrap">
                    <div class="funil-row__bar" style="width:${f.pct}%; background:${f.color}">
                        ${f.pct}%
                    </div>
                </div>
                <div class="funil-row__count">${f.count}</div>
                <div class="funil-row__delta ${f.delta ? 'neg' : ''}">${f.delta}</div>
            </div>
        `).join('');
    }

});
