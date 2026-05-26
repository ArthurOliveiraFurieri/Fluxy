-- ── Etapas do Funil ─────────────────────────────
INSERT INTO etapa_funil (id, nome, percentual, usuarios, drop_percentual, drop_usuarios, cor, ordem) VALUES
(1, 'Entrada no Site',       100, 142500, 31, 44100, '#3b82f6', 1),
(2, 'Exploração de Conteúdo', 69,  98400, 47, 46300, '#60a5fa', 2),
(3, 'Engajamento Profundo',   53,  52100, 56, 29300, '#8b5cf6', 3),
(4, 'Intenção de Conversão',  44,  22800, 59, 13300, '#f59e0b', 4),
(5, 'Conversão / Cadastro',   41,   9400,  0,     0, '#10b981', 5);

-- ── Eventos Críticos ────────────────────────────
INSERT INTO evento_critico (id, nome, descricao, usuarios_afetados, severidade) VALUES
(1, 'Cadastro Avançado',  'Alto abandono pós formulário',    4820, 'red'),
(2, 'Cursos Online',      'Queda de conclusão (-12%)',        3140, 'yellow'),
(3, 'Ferramentas',        'Pico de engajamento: 10h-11h',    8920, 'blue'),
(4, 'Consultorias MEI',   'Maior retenção do funil',          2310, 'green'),
(5, 'Pesquisa Interna',   'Rage clicks detectados',           4820, 'red');

-- ── Taxa de Abandono por Página ─────────────────
INSERT INTO abandono_pagina (id, nome, percentual, cor) VALUES
(1, 'Cadastro Avançado', 82, '#ef4444'),
(2, 'Busca Interna',     68, '#f97316'),
(3, 'Cursos Online',     62, '#fb923c'),
(4, 'Portal de Serviços',52, '#fbbf24'),
(5, 'Artigos / Blog',    43, '#fcd34d'),
(6, 'Ferramentas',       18, '#10b981');

-- ── Micro-feedback por Etapa ────────────────────
INSERT INTO micro_feedback (id, etapa, positivo, negativo, neutro) VALUES
(1, 'Entrada',      82, 12,  6),
(2, 'Exploração',   71, 18, 11),
(3, 'Engajamento',  64, 21, 15),
(4, 'Conversão',    53, 24, 23),
(5, 'Pós-Cadastro', 68, 19, 13);