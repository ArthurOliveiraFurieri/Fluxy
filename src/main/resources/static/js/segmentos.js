document.addEventListener('DOMContentLoaded', function () {
    renderSegmentos();
});

function renderSegmentos() {

    fetch('/api/segmentos')
        .then(response => response.json())
        .then(segmentos => {

            const tbody = document.getElementById('segmentosTableBody');

            if (!tbody) return;

            tbody.innerHTML = segmentos.map(s => {

                let oportunidade = 'Desenvolver';
                let classe = 'status-warning';

                if (s.retorno >= 55) {
                    oportunidade = 'Alto potencial';
                    classe = 'status-success';
                }

                if (s.retorno <= 35) {
                    oportunidade = 'Precisa atenção';
                    classe = 'status-danger';
                }

                return `
                    <tr>
                        <td>
                            <span class="dot"
                                  style="background:${s.cor};"></span>
                            ${s.nome}
                        </td>

                        <td>
                            <div class="progress-wrapper">
                                <div class="progress-bar">
                                    <div class="progress-fill"
                                         style="width:${s.pct}%;
                                                background:${s.cor};">
                                    </div>
                                </div>
                                <span>${s.pct}%</span>
                            </div>
                        </td>

                        <td class="val-success">
                            ${s.engajamento}%
                        </td>

                        <td>
                            ${s.retorno}%
                        </td>

                        <td>
                            <span class="badge-status ${classe}">
                                ${oportunidade}
                            </span>
                        </td>
                    </tr>
                `;
            }).join('');
        })
        .catch(error => {
            console.error('Erro ao carregar segmentos:', error);
        });
}