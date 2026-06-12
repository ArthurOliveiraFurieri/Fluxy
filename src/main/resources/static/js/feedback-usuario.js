/* feedback-usuario.js — NPS (gauge + gráfico) via backend */

document.addEventListener("DOMContentLoaded", () => {
    carregarNps();
    carregarFrustracao();
    carregarDepoimentos();
    carregarSugestoes();
    carregarEffort();

    const btnRefresh = document.getElementById('btnRefresh');
    if (btnRefresh) {
        btnRefresh.addEventListener('click', function () {
            fetch('/api/regenerar', { method: 'POST' })
                .then(response => response.json())
                .then(() => location.reload())
                .catch(err => console.error('Erro ao regenerar:', err));
        });
    }
});
let npsChartInstance = null;

function carregarNps() {
    fetch('/api/nps')
        .then(r => r.json())
        .then(dados => {
            atualizarGauge(dados);
            renderGrafico(dados);
        })
        .catch(err => console.error('Erro ao carregar NPS:', err));
}

/* ── Gauge: usa o último mês como NPS atual ─────── */
function atualizarGauge(dados) {
    if (!dados.length) return;

    const npsAtual = dados[dados.length - 1].valor;

    // Número grande
    const valueEl = document.querySelector('.nps-gauge__value');
    if (valueEl) valueEl.textContent = npsAtual;

    // Rótulo conforme faixa
    const labelEl = document.querySelector('.nps-gauge__label');
    if (labelEl) labelEl.textContent = classificarNps(npsAtual);

    // Ponteiro: NPS 0-100 → ângulo -90° a +90°
    const needle = document.querySelector('.nps-gauge__needle');
    if (needle) {
        const angulo = (npsAtual / 100) * 180 - 90;
        needle.style.transform = `rotate(${angulo}deg)`;
    }

    // Distribuição proporcional (promotores/neutros/detratores)
    const promotores = npsAtual;
    const detratores = Math.max(0, Math.round((100 - npsAtual) * 0.3));
    const neutros    = 100 - promotores - detratores;

    const itens = document.querySelectorAll('.nps-summary__item strong');
    if (itens.length === 3) {
        itens[0].textContent = `${promotores}%`;
        itens[1].textContent = `${neutros}%`;
        itens[2].textContent = `${detratores}%`;
    }
}

function classificarNps(v) {
    if (v >= 70) return 'Excelente';
    if (v >= 50) return 'Muito bom';
    if (v >= 30) return 'Razoável';
    if (v >= 0)  return 'Precisa melhorar';
    return 'Crítico';
}

/* ── Gráfico de evolução ────────────────────────── */
function renderGrafico(dados) {
    const canvas = document.getElementById("npsEvolutionChart");
    if (!canvas || typeof Chart === "undefined") return;

    if (npsChartInstance) npsChartInstance.destroy();

    npsChartInstance = new Chart(canvas, {
        type: "line",
        data: {
            labels: dados.map(d => d.mes),
            datasets: [{
                label: "NPS",
                data: dados.map(d => d.valor),
                borderColor: "#10b981",
                backgroundColor: "rgba(16, 185, 129, 0.12)",
                borderWidth: 2,
                pointRadius: 4,
                pointBackgroundColor: "#10b981",
                tension: 0.35,
                fill: true
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: { display: false },
                tooltip: { enabled: true }
            },
            scales: {
                x: {
                    grid: { color: "rgba(144, 161, 185, 0.18)" },
                    ticks: { color: "#90a1b9", font: { size: 10 } }
                },
                y: {
                    min: 0, max: 100,
                    grid: { color: "rgba(144, 161, 185, 0.18)" },
                    ticks: { stepSize: 25, color: "#90a1b9", font: { size: 10 } }
                }
            }
        }
    });

    atualizarTrendNote(dados);   
}

/* ── Indicadores de Frustração ──────────────────── */
function carregarFrustracao() {
    fetch('/api/frustracao')
        .then(r => r.json())
        .then(dados => {
            const tbody = document.getElementById('frustracaoBody');
            if (!tbody) return;

            tbody.innerHTML = dados.map(f => {
                const trendClass = f.tendencia.startsWith('+') ? 'trend-down' : 'trend-up';
                const sevClass   = f.severidade === 'critical' ? 'critical' : 'warning';
                const sevLabel   = f.severidade === 'critical' ? 'Crítico' : 'Atenção';

                return `
                    <tr>
                        <td>${f.evento}</td>
                        <td>${f.pagina}</td>
                        <td>${f.ocorrencias.toLocaleString('pt-BR')}</td>
                        <td class="${trendClass}">${f.tendencia}</td>
                        <td><span class="severity-badge ${sevClass}">${sevLabel}</span></td>
                    </tr>
                `;
            }).join('');
        })
        .catch(err => console.error('Erro ao carregar frustração:', err));
}

/* ── Depoimentos ────────────────────────────────── */
function carregarDepoimentos() {
    fetch('/api/depoimentos')
        .then(r => r.json())
        .then(dados => {
            const container = document.getElementById('depoimentosList');
            if (!container) return;

            container.innerHTML = dados.map(d => `
                <div class="testimonial-item ${d.tipo}">
                    <p class="testimonial-text">"${d.texto}"</p>
                    <span class="testimonial-user">${d.usuario}</span>
                </div>
            `).join('');
        })
        .catch(err => console.error('Erro ao carregar depoimentos:', err));
}

/* ── Sugestões Recorrentes ──────────────────────── */
function carregarSugestoes() {
    fetch('/api/sugestoes')
        .then(r => r.json())
        .then(dados => {
            const container = document.getElementById('sugestoesList');
            if (!container) return;

            container.innerHTML = dados.map(s => `
                <div class="suggestion-item">
                    <strong>${s.titulo}</strong>
                    <span>${s.descricao}</span>
                </div>
            `).join('');
        })
        .catch(err => console.error('Erro ao carregar sugestões:', err));
}

/* ── Effort Score ───────────────────────────────── */
function carregarEffort() {
    fetch('/api/effort')
        .then(r => r.json())
        .then(dados => {
            const container = document.getElementById('effortList');
            if (!container) return;

            container.innerHTML = dados.map(e => `
                <div class="effort-row">
                    <span>${e.label}</span>
                    <div class="effort-track">
                        <div class="effort-fill ${e.cor}" style="width:${e.percentual}%">${e.percentual}%</div>
                    </div>
                </div>
            `).join('');

            atualizarEffortNote(dados);   // ← adiciona aqui
        })
        .catch(err => console.error('Erro ao carregar effort:', err));
}

/* ── Descrição dinâmica da tendência do NPS ─────── */
function atualizarTrendNote(dados) {
    const note = document.getElementById('npsTrendNote');
    if (!note || dados.length < 2) return;

    const primeiro = dados[0].valor;
    const ultimo   = dados[dados.length - 1].valor;
    const diff     = ultimo - primeiro;

    // Verifica se a série é sempre crescente (consistente)
    let sempreSubindo = true;
    for (let i = 1; i < dados.length; i++) {
        if (dados[i].valor < dados[i - 1].valor) {
            sempreSubindo = false;
            break;
        }
    }

    let texto, classe;

    if (diff >= 10 && sempreSubindo) {
        texto  = `Crescimento consistente de satisfação nos últimos ${dados.length} meses.`;
        classe = 'trend-positive';
    } else if (diff >= 10) {
        texto  = `Alta expressiva na satisfação, com algumas oscilações no período.`;
        classe = 'trend-positive';
    } else if (diff > 2) {
        texto  = `Tendência de leve melhora na satisfação dos usuários.`;
        classe = 'trend-positive';
    } else if (diff >= -2) {
        texto  = `Satisfação estável ao longo dos últimos ${dados.length} meses.`;
        classe = 'trend-neutral';
    } else if (diff > -10) {
        texto  = `Atenção: leve queda na satisfação dos usuários.`;
        classe = 'trend-negative';
    } else {
        texto  = `Alerta: queda significativa na satisfação no período.`;
        classe = 'trend-negative';
    }

    note.textContent = texto;
    note.className = 'trend-note ' + classe;
}

/* ── Descrição dinâmica do Effort Score ─────────── */
function atualizarEffortNote(dados) {
    const note = document.getElementById('effortNote');
    if (!note || dados.length < 5) return;

    // dados[0]=Muito fácil, [1]=Fácil, [2]=Neutro, [3]=Difícil, [4]=Muito difícil
    const facil    = dados[0].percentual + dados[1].percentual;
    const neutro   = dados[2].percentual;
    const dificil  = dados[3].percentual + dados[4].percentual;

    let texto, classe;

    if (facil >= 60) {
        texto  = 'A maioria dos usuários considera a experiência fácil.';
        classe = 'effort-positive';
    } else if (dificil >= 40) {
        texto  = 'Atenção: parte significativa dos usuários relata dificuldade.';
        classe = 'effort-negative';
    } else if (neutro >= 40) {
        texto  = 'Experiência percebida como mediana pela maioria dos usuários.';
        classe = 'effort-neutral';
    } else {
        texto  = 'Percepção de esforço dividida entre os usuários.';
        classe = 'effort-neutral';
    }

    note.textContent = texto;
    note.className = 'effort-note ' + classe;
}