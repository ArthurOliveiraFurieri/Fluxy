document.addEventListener('DOMContentLoaded', function () {

    Chart.defaults.font.family = "'Segoe UI', system-ui, sans-serif";
    Chart.defaults.font.size   = 10;
    Chart.defaults.color       = '#9ca3af';

    carregarTudo();

    const btnRefresh = document.getElementById('btnRefresh');
    if (btnRefresh) {
        btnRefresh.addEventListener('click', function () {
            fetch('/api/regenerar', { method: 'POST' })
                .then(r => r.json())
                .then(() => location.reload())
                .catch(err => console.error('Erro ao regenerar:', err));
        });
    }
});

function carregarTudo() {
    Promise.all([
        fetch('/api/visao/insights').then(r => r.json()),
        fetch('/api/visao/kpis').then(r => r.json()),
        fetch('/api/visao/evolucao').then(r => r.json()),
        fetch('/api/visao/fontes').then(r => r.json()),
        fetch('/api/visao/funil').then(r => r.json()),
    ])
    .then(([insights, kpis, evolucao, fontes, funil]) => {
        renderInsights(insights);
        renderKpis(kpis);
        renderEvolucao(evolucao);
        renderVolume(evolucao);
        renderDonut(fontes);
        renderFunil(funil);
    })
    .catch(err => console.error('Erro ao carregar visão geral:', err));
}

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

let chartEvolucaoInstance = null;
function renderEvolucao(dados) {
    const ctx = document.getElementById('chartEvolucao');
    if (!ctx) return;

    if (chartEvolucaoInstance) chartEvolucaoInstance.destroy();

    const labels = dados.map(d => d.dia);

    chartEvolucaoInstance = new Chart(ctx, {
        type: 'line',
        data: {
            labels,
            datasets: [
                {
                    label: 'Engajamento %',
                    data: dados.map(d => d.engajamento),
                    borderColor: '#3b82f6',
                    backgroundColor: 'rgba(59,130,246,.08)',
                    fill: true, tension: .4, borderWidth: 2,
                    pointRadius: 0, pointHoverRadius: 4,
                },
                {
                    label: 'Retorno %',
                    data: dados.map(d => d.retorno),
                    borderColor: '#10b981',
                    backgroundColor: 'rgba(16,185,129,.05)',
                    fill: true, tension: .4, borderWidth: 2,
                    pointRadius: 0, pointHoverRadius: 4,
                },
            ],
        },
        options: commonOptions,
    });
}

let chartVolumeInstance = null;
function renderVolume(dados) {
    const ctx = document.getElementById('chartVolume');
    if (!ctx) return;

    if (chartVolumeInstance) chartVolumeInstance.destroy();

    chartVolumeInstance = new Chart(ctx, {
        type: 'line',
        data: {
            labels: dados.map(d => d.dia),
            datasets: [{
                label: 'Volume de Sessões',
                data: dados.map(d => d.volume),
                borderColor: '#7c3aed',
                backgroundColor: 'rgba(124,58,237,.06)',
                fill: true, tension: .4, borderWidth: 2,
                pointRadius: 0, pointHoverRadius: 4,
            }],
        },
        options: commonOptions,
    });
}


function renderInsights(insights) {
    const container = document.getElementById('insightsList');
    if (!container) return;

    container.innerHTML = insights.map(i => `
        <div class="insight-card ${i.tipo}">
            <div><span class="badge ${i.badgeCor}">${i.badge}</span></div>
            <h3>${i.titulo}</h3>
            <p>${i.descricao}</p>
            <a href="#">${i.link}</a>
        </div>
    `).join('');
}

function renderKpis(kpis) {
    const container = document.getElementById('kpisGrid');
    if (!container) return;

    container.innerHTML = kpis.map(k => {
        const direction = k.trendDirection === 'up' ? 'up' : 'down';
        const arrow = direction === 'up' ? '↑' : '↓';
        const sinal = k.tendencia >= 0 ? '+' : '';
        return `
            <div class="kpi-card">
                <div class="kpi-card__header">
                    <span class="kpi-card__label">${k.label}</span>
                    <div class="kpi-card__icon" style="background:${k.iconeBg};">
                        <svg viewBox="0 0 24 24" fill="none" stroke="${k.iconeCor}" stroke-width="2">
                            <circle cx="12" cy="12" r="10"/><polyline points="12,6 12,12 16,14"/>
                        </svg>
                    </div>
                </div>
                <div class="kpi-card__value">${k.valor} <span class="unit">${k.unidade}</span></div>
                <div class="kpi-card__desc">${k.descricao}</div>
                <div class="kpi-card__trend ${direction}">${arrow} ${sinal}${k.tendencia}% <span class="vs">vs período anterior</span></div>
            </div>
        `;
    }).join('');
}

let chartDonutInstance = null;
function renderDonut(fontes) {
    const ctx = document.getElementById('chartDonut');
    if (!ctx) return;
    
    if (chartDonutInstance) chartDonutInstance.destroy();

    chartDonutInstance = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: fontes.map(f => f.label),
            datasets: [{
                data: fontes.map(f => f.percentual),
                backgroundColor: fontes.map(f => f.cor),
                borderWidth: 2,
                borderColor: '#fff'
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: { legend: { position: 'bottom', labels: { boxWidth: 12 } } }
        }
    });
}

function renderFunil(etapas) {
    const container = document.getElementById('funilResumidoList');
    if (!container) return;

    etapas.sort((a, b) => a.ordem - b.ordem);

    container.innerHTML = etapas.map((e, i) => {
        let delta = '';
        if (i > 0) {
            const anterior = etapas[i - 1].percentual;
            delta = Math.round(((e.percentual - anterior) / anterior) * 100) + '%';
        }
        const count = e.usuarios ? e.usuarios.toLocaleString('pt-BR') : '';
        return `
            <div class="funil-row">
                <div class="funil-row__label">${e.nome}</div>
                <div class="funil-row__bar-wrap">
                    <div class="funil-row__bar" style="width:${e.percentual}%; background:${e.cor}">${e.percentual}%</div>
                </div>
                <div class="funil-row__count">${count}</div>
                <div class="funil-row__delta ${delta.startsWith('-') ? 'neg' : ''}">${delta}</div>
            </div>
        `;
    }).join('');
}