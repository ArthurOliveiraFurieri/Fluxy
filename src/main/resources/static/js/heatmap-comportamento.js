/* heatmap-comportamento.js — dados via backend */

document.addEventListener("DOMContentLoaded", function () {
    carregarHeatmap();
    carregarElementos();
    carregarScroll();

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

/* Mapa de Calor (6 secoes) */
function carregarHeatmap() {
    const secoes = [
        { nome: 'Navegação',     url: '/api/heatmap/navegacao' },
        { nome: 'Hero',          url: '/api/heatmap/hero' },
        { nome: 'Acesso Rápido', url: '/api/heatmap/acesso-rapido' },
        { nome: 'Conteúdo',      url: '/api/heatmap/conteudo' },
        { nome: 'Banner',        url: '/api/heatmap/banner' },
        { nome: 'Rodapé',        url: '/api/heatmap/rodape' },
    ];

    const grid = document.getElementById('heatmapGrid');
    if (!grid) return;

    Promise.all(secoes.map(s => fetch(s.url).then(r => r.json())))
        .then(resultados => {
            grid.innerHTML = secoes.map((s, i) => {
                const blocos = resultados[i].map(b =>
                    '<div class="block ' + b.intensidade + '">' + (b.nome || '') + '</div>'
                ).join('');
                return '<div class="grid-row"><div class="row-label">' + s.nome +
                       '</div><div class="blocks-area">' + blocos + '</div></div>';
            }).join('');
        })
        .catch(err => console.error('Erro heatmap:', err));
}

/* Elementos Mais Clicados + Insights derivados */
function carregarElementos() {
    fetch('/api/heatmap/elementos')
        .then(r => r.json())
        .then(dados => {
            renderElementos(dados);
            gerarInsights(dados);
        })
        .catch(err => console.error('Erro elementos:', err));
}

function renderElementos(dados) {
    const container = document.getElementById('elementosList');
    if (!container) return;

    container.innerHTML = dados.map(e => {
        const fill = e.percentual >= 90 ? 'fill-muito-alto' : 'fill-alto';
        return '<div class="bar-row"><div class="rank-number">' + e.rank +
               '</div><div class="bar-info"><div class="bar-label-group">' +
               '<span class="bar-name">' + e.nome + '</span>' +
               '<span class="bar-percent">' + e.percentual + '%</span></div>' +
               '<div class="bar-container"><div class="bar-fill ' + fill +
               '" style="width:' + e.percentual + '%;"></div></div></div></div>';
    }).join('');
}

function gerarInsights(dados) {
    const container = document.getElementById('insightsList');
    if (!container || !dados.length) return;

    const ordenado = [...dados].sort((a, b) => b.percentual - a.percentual);
    const maior = ordenado[0];
    const menor = ordenado[ordenado.length - 1];

    const insights = [
        {
            cor: 'insight-red',
            titulo: '"' + maior.nome + '" tem ' + maior.percentual + '% de intensidade',
            descricao: 'Principal ponto de engajamento. Considere ampliar a oferta visível na landing.'
        },
        {
            cor: 'insight-yellow',
            titulo: menor.nome + ' com ' + menor.percentual + '% - padrão de intenção',
            descricao: 'Item com menor intensidade relativa. Avalie melhorar posicionamento e visibilidade.'
        },
        {
            cor: 'insight-blue',
            titulo: 'Área editorial com <25% de cliques',
            descricao: 'Conteúdo editorial tem baixo engajamento relativo. Revisar posicionamento e títulos.'
        }
    ];

    container.innerHTML = insights.map(i =>
        '<div class="insight-box ' + i.cor + '"><strong>' + i.titulo +
        '</strong><small>' + i.descricao + '</small></div>'
    ).join('');
}

/* Scroll (barras + analise) */
function carregarScroll() {
    fetch('/api/heatmap/scroll')
        .then(r => r.json())
        .then(dados => {
            renderScrollBars(dados);
            renderScrollAnalysis(dados);
            renderScrollCallout(dados);
        })
        .catch(err => console.error('Erro scroll:', err));
}

function renderScrollBars(dados) {
    const container = document.getElementById('scrollBars');
    if (!container) return;

    container.innerHTML = dados.map(s =>
        '<div class="v-bar-group"><div class="v-bar ' + s.cor +
        '" style="height:' + s.percentual + '%;"></div>' +
        '<span class="x-axis-label">' + s.faixa + '</span></div>'
    ).join('');
}

function renderScrollAnalysis(dados) {
    const container = document.getElementById('scrollAnalysis');
    if (!container) return;

    container.innerHTML = dados.map(s =>
        '<div class="analysis-row"><div class="analysis-headers">' +
        '<span class="analysis-title">' + s.titulo + '</span>' +
        '<span class="analysis-val">' + s.percentual + '%</span></div>' +
        '<div class="analysis-subtitle">' + s.faixa + ' da página</div>' +
        '<div class="analysis-progress-bg"><div class="analysis-progress-fill ' + s.cor +
        '" style="width:' + s.percentual + '%;"></div></div></div>'
    ).join('');
}

function renderScrollCallout(dados) {
    const container = document.getElementById('scrollCallout');
    if (!container || dados.length < 2) return;

    const naoChegamMetade = 100 - dados[1].percentual;

    let texto, classe;

    if (naoChegamMetade >= 60) {
        texto = '<strong>' + naoChegamMetade + '% dos usuários</strong> não chegam à metade da página. Conteúdo crítico deve estar na parte superior (acima do fold).';
        classe = 'callout-critical';
    } else if (naoChegamMetade >= 35) {
        texto = '<strong>' + naoChegamMetade + '% dos usuários</strong> não chegam à metade da página. Considere revisar a hierarquia de conteúdo.';
        classe = 'callout-warning';
    } else {
        texto = '<strong>' + (100 - naoChegamMetade) + '% dos usuários</strong> chegam até a metade da página. Boa retenção de scroll inicial.';
        classe = 'callout-positive';
    }

    container.innerHTML = texto;
    container.className = 'scroll-callout-box ' + classe;
}
