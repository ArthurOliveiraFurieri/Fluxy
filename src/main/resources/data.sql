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

-- ── Engajamento por Hora ─────────────────────────
INSERT INTO engajamento_hora (id, hora, valor, peak) VALUES
( 1, '06h',  800, false),
( 2, '07h', 1200, false),
( 3, '08h', 1900, false),
( 4, '09h', 2600, false),
( 5, '10h', 3400, true),
( 6, '11h', 3600, true),
( 7, '12h', 2800, false),
( 8, '13h', 3200, true),
( 9, '14h', 3300, true),
(10, '15h', 2900, false),
(11, '16h', 2500, false),
(12, '17h', 2200, false),
(13, '18h', 2000, false),
(14, '19h', 1700, false),
(15, '20h', 1400, false),
(16, '21h',  900, false);

-- ── Conteúdo de Engajamento ─────────────────────
INSERT INTO conteudo_engajamento (id, label, percentual, cor) VALUES
(1, 'Ferramentas',   81, '#22c55e'),
(2, 'Consultorias',  76, '#6366f1'),
(3, 'Cursos Online', 72, '#6366f1'),
(4, 'Eventos',       68, '#f59e0b'),
(5, 'Artigos/Blog',  54, '#ef4444');

-- ── Retenção por Segmento ───────────────────────
INSERT INTO retencao_segmento (id, label, cor, semana1, semana2, semana3, semana4) VALUES
(1, 'Consolidados',   '#f59e0b', 100, 68, 54, 44),
(2, 'MEI',            '#6366f1', 100, 72, 58, 46),
(3, 'Estudantes',     '#22c55e', 100, 60, 44, 34),
(4, 'Empreendedores', '#a78bfa', 100, 65, 50, 40);

-- ── Radar por Segmento ──────────────────────────
INSERT INTO radar_segmento (id, label, cor, val1, val2, val3, val4, val5, val6) VALUES
(1, 'Consolidados',   '#f59e0b', 82, 70, 75, 65, 80, 60),
(2, 'MEI',            '#6366f1', 70, 78, 68, 72, 65, 82),
(3, 'Estudantes',     '#22c55e', 60, 55, 78, 50, 68, 72),
(4, 'Empreendedores', '#a78bfa', 75, 68, 65, 80, 58, 68);

-- ── Segmentos ───────────────────────────────────
INSERT INTO segmento (id, nome, cor, pct, engajamento, retorno, sessoes) VALUES
(1, 'Empreendedores',    '#6366f1', 38, 71, 45, 54150),
(2, 'MEI',               '#22c55e', 27, 74, 52, 38475),
(3, 'Estudantes',        '#a78bfa', 18, 48, 22,  7125),
(4, 'Emp. Consolidados', '#f59e0b', 12, 62, 33, 25650),
(5, 'Iniciantes',        '#3b82f6',  5, 79, 58, 17100),
(6, 'Outros',            '#9ca3af',  5, 48, 22,  7125);