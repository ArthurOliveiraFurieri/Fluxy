/* feedback-usuario.js — NPS (gauge + gráfico) via backend */

document.addEventListener("DOMContentLoaded", () => {
    carregarNps();

    const btnRefresh = document.getElementById('btnRefresh');
    if (btnRefresh) {
        btnRefresh.addEventListener('click', function () {
            fetch('/api/regenerar', { method: 'POST' })
                .then(response => response.json())  // espera a resposta completa
                .then(() => {
                    location.reload();
                })
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
}