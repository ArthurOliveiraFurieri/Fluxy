/* jornada-usuario.js — dados dinâmicos da Jornada do Usuário */

document.addEventListener('DOMContentLoaded', function () {

    carregarFunil();
    carregarEventos();
    carregarAbandono();
    carregarFeedback();

    setInterval(function () {
        carregarFunil();
        carregarEventos();
        carregarAbandono();
        carregarFeedback();
    }, 30000);

});

function carregarFunil() {
    fetch('/api/funil')
        .then(r => r.json())
        .then(etapas => {
            const container = document.getElementById('funilSteps');
            if (!container) return;

            etapas.sort((a, b) => a.ordem - b.ordem);

            container.innerHTML = etapas.map((e, i) => {
                const isUltima = i === etapas.length - 1;
                const usuarios = formatarNumero(e.usuarios);
                const drop = !isUltima
                    ? `<div class="funnel-step__drop">↓ -${e.dropPercentual}% saíram (${formatarNumero(e.dropUsuarios)} usuários)</div>`
                    : '';

                return `
                    <div class="funnel-step">
                        <div class="funnel-step__row">
                            <div class="funnel-step__num" style="background:${e.cor};">${e.ordem}</div>
                            <div class="funnel-step__bar-wrap">
                                <div class="funnel-step__bar" style="background:${e.cor}; width:${e.percentual}%;">
                                    <span class="bar-label">${e.nome}</span>
                                    <span class="bar-pct">${e.percentual}%</span>
                                </div>
                            </div>
                            <div class="funnel-step__users">${usuarios}<small>usuários</small></div>
                        </div>
                        ${drop}
                    </div>
                `;
            }).join('');
        })
        .catch(err => console.error('Erro ao carregar funil:', err));
}

function carregarEventos() {
    fetch('/api/eventos')
        .then(r => r.json())
        .then(eventos => {
            const container = document.getElementById('eventosList');
            if (!container) return;

            container.innerHTML = eventos.map(e => {
                const icon = getEventoIcon(e.severidade);
                const afetados = formatarNumero(e.usuariosAfetados);

                return `
                    <div class="event-item ev-${e.severidade}">
                        <div class="event-item__icon">${icon}</div>
                        <div class="event-item__body">
                            <div class="event-item__name">${e.nome}</div>
                            <div class="event-item__desc">${e.descricao}</div>
                            <div class="event-item__count">${afetados} usuários afetados</div>
                        </div>
                    </div>
                `;
            }).join('');
        })
        .catch(err => console.error('Erro ao carregar eventos:', err));
}

function carregarAbandono() {
    fetch('/api/abandono')
        .then(r => r.json())
        .then(paginas => {
            const container = document.getElementById('abandonoList');
            if (!container) return;

            container.innerHTML = paginas.map(p => `
                <div class="abandon-row">
                    <div class="abandon-row__header">
                        <span class="abandon-row__label">${p.nome}</span>
                        <span class="abandon-row__pct">${p.percentual}%</span>
                    </div>
                    <div class="abandon-row__track">
                        <div class="abandon-row__fill" style="width:${p.percentual}%; background:${p.cor};"></div>
                    </div>
                </div>
            `).join('');
        })
        .catch(err => console.error('Erro ao carregar abandono:', err));
}

function carregarFeedback() {
    fetch('/api/feedback')
        .then(r => r.json())
        .then(feedbacks => {
            const container = document.getElementById('feedbackList');
            if (!container) return;

            container.innerHTML = feedbacks.map(f => `
                <div class="mf-row">
                    <div class="mf-row__header">
                        <span class="mf-row__label">${f.etapa}</span>
                    </div>
                    <div class="mf-row__track">
                        <div class="mf-row__seg" style="width:${f.positivo}%; background:#10b981;">${f.positivo}%</div>
                        <div class="mf-row__seg" style="width:${f.negativo}%; background:#ef4444;">${f.negativo}%</div>
                        <div class="mf-row__seg" style="width:${f.neutro}%; background:#f59e0b;">${f.neutro}%</div>
                    </div>
                </div>
            `).join('');
        })
        .catch(err => console.error('Erro ao carregar feedback:', err));
}

function formatarNumero(n) {
    if (n >= 1000) return (n / 1000).toFixed(1) + 'k';
    return n.toString();
}

function getEventoIcon(severidade) {
    const icons = {
        red: `<svg viewBox="0 0 24 24" fill="none" stroke="white" stroke-width="2">
                <path d="M10.29 3.86L1.82 18a2 2 0 001.71 3h16.94a2 2 0 001.71-3L13.71 3.86a2 2 0 00-3.42 0z"/>
                <line x1="12" y1="9" x2="12" y2="13"/><line x1="12" y1="17" x2="12.01" y2="17"/>
              </svg>`,
        yellow: `<svg viewBox="0 0 24 24" fill="none" stroke="white" stroke-width="2">
                <path d="M10.29 3.86L1.82 18a2 2 0 001.71 3h16.94a2 2 0 001.71-3L13.71 3.86a2 2 0 00-3.42 0z"/>
                <line x1="12" y1="9" x2="12" y2="13"/><line x1="12" y1="17" x2="12.01" y2="17"/>
              </svg>`,
        blue: `<svg viewBox="0 0 24 24" fill="none" stroke="white" stroke-width="2">
                <polyline points="22 12 18 12 15 21 9 3 6 12 2 12"/>
              </svg>`,
        green: `<svg viewBox="0 0 24 24" fill="none" stroke="white" stroke-width="2.5">
                <polyline points="20 6 9 17 4 12"/>
              </svg>`,
    };
    return icons[severidade] || icons.blue;
}