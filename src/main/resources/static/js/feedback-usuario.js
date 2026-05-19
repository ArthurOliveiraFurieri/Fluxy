document.addEventListener("DOMContentLoaded", () => {
    const canvas = document.getElementById("npsEvolutionChart");

    if (!canvas || typeof Chart === "undefined") {
        return;
    }

    new Chart(canvas, {
        type: "line",
        data: {
            labels: ["Jan", "Fev", "Mar", "Abr"],
            datasets: [
                {
                    label: "NPS",
                    data: [54, 61, 67, 72],
                    borderColor: "#10b981",
                    backgroundColor: "rgba(16, 185, 129, 0.12)",
                    borderWidth: 2,
                    pointRadius: 4,
                    pointBackgroundColor: "#10b981",
                    tension: 0.35,
                    fill: true
                }
            ]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: {
                    display: false
                },
                tooltip: {
                    enabled: true
                }
            },
            scales: {
                x: {
                    grid: {
                        color: "rgba(144, 161, 185, 0.18)"
                    },
                    ticks: {
                        color: "#90a1b9",
                        font: {
                            size: 10
                        }
                    }
                },
                y: {
                    min: 0,
                    max: 100,
                    grid: {
                        color: "rgba(144, 161, 185, 0.18)"
                    },
                    ticks: {
                        stepSize: 25,
                        color: "#90a1b9",
                        font: {
                            size: 10
                        }
                    }
                }
            }
        }
    });
});