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
(1, 'Empreendedores', '#6366f1', 38, 71, 45, 54150),
(2, 'MEI',               '#22c55e', 27, 74, 52, 38475),
(3, 'Estudantes',        '#a78bfa', 18, 48, 22,  7125),
(4, 'Emp. Consolidados', '#f59e0b', 12, 62, 33, 25650),
(5, 'Iniciantes',        '#3b82f6',  5, 79, 58, 17100),
(6, 'Outros',            '#9ca3af',  5, 48, 22,  7125);

INSERT INTO elemento_clicado (id, rank, nome, percentual) VALUES
(1, 1, 'Cursos Gratuitos', 94),
(2, 2, 'Cursos', 92),
(3, 3, 'Portal MEI', 88),
(4, 4, 'Busca', 84);

INSERT INTO insight_comportamento (id, tipo, titulo, descricao) VALUES
(1, 'red', '"Cursos Gratuitos" tem 94% de intensidade', 'Principal ponto de engajamento. Considere ampliar a oferta visível na landing.'),
(2, 'yellow', 'Busca com 84% - padrão de intenção', 'Alta intenção de busca indica necessidade de melhoria no mecanismo de discovery.'),
(3, 'blue', 'Área editorial com <25% de cliques', 'Conteúdo editorial tem baixo engajamento relativo. Revisar posicionamento e títulos.');

-- ── NPS Evolução ────────────────────────────────
INSERT INTO nps_evolucao (id, mes, valor) VALUES
(1, 'Jan', 54),
(2, 'Fev', 61),
(3, 'Mar', 67),
(4, 'Abr', 72);
-- ── Indicadores de Frustração ───────────────────
INSERT INTO indicador_frustracao (id, evento, pagina, ocorrencias, tendencia, severidade) VALUES
(1, 'Rage clicks',              'Cadastro avançado',    4820, '+18%', 'critical'),
(2, 'Dead clicks',              'Página inicial',       3140, '+12%', 'warning'),
(3, 'Abandono de formulário',   'Inscrição em cursos',  2780, '-6%',  'warning'),
(4, 'Erro de carregamento',     'Portal de serviços',   1960, '-9%',  'critical'),
(5, 'Navegação circular',       'Busca interna',        1420, '+7%',  'critical'),
(6, 'Voltar imediatamente',     'Resultados de busca',   980, '+4%',  'warning');

-- ── Sugestões Recorrentes ───────────────────────
INSERT INTO sugestao (id, titulo, descricao) VALUES
(1, 'Simplificar cadastro',     'Reduzir campos obrigatórios e explicar cada etapa.'),
(2, 'Melhorar busca',           'Priorizar resultados mais relevantes e filtros rápidos.'),
(3, 'Evitar erros no portal',   'Revisar carregamento das páginas de serviços.'),
(4, 'Guiar novos usuários',     'Adicionar mensagens de orientação durante o fluxo.');

-- ── Depoimentos (20 variados) ───────────────────
INSERT INTO depoimento (id, texto, usuario, tipo) VALUES
(1,  'A navegação ficou simples e consegui encontrar rapidamente o que precisava.', 'Ana Clara · MEI', 'positive'),
(2,  'O formulário de cadastro é longo e algumas etapas não ficam claras.', 'Roberto · Empreendedor', 'negative'),
(3,  'Gostei dos conteúdos recomendados. Pareciam bem alinhados ao meu perfil.', 'Marina · Estudante', 'positive'),
(4,  'A busca interna poderia mostrar resultados mais objetivos.', 'Lucas · Usuário recorrente', 'warning'),
(5,  'Plataforma muito intuitiva, consegui me cadastrar em poucos minutos.', 'Fernanda · MEI', 'positive'),
(6,  'Tive dificuldade para encontrar o suporte quando precisei de ajuda.', 'Carlos · Empreendedor', 'negative'),
(7,  'Os cursos são excelentes e bem didáticos, recomendo muito.', 'Juliana · Estudante', 'positive'),
(8,  'A página de pagamento travou algumas vezes durante o processo.', 'Pedro · Consolidado', 'negative'),
(9,  'Experiência razoável, mas poderia ter mais filtros na busca.', 'Beatriz · MEI', 'warning'),
(10, 'Adorei a interface, muito moderna e fácil de usar.', 'Rafael · Empreendedor', 'positive'),
(11, 'Demorou muito para carregar em alguns momentos.', 'Camila · Estudante', 'negative'),
(12, 'O conteúdo é bom, mas a organização poderia melhorar.', 'Thiago · Usuário recorrente', 'warning'),
(13, 'Consegui resolver meu problema rapidamente, muito eficiente.', 'Larissa · MEI', 'positive'),
(14, 'Senti falta de um tutorial inicial para novos usuários.', 'Bruno · Iniciante', 'warning'),
(15, 'As consultorias foram fundamentais para o meu negócio.', 'Patrícia · Consolidado', 'positive'),
(16, 'Algumas funcionalidades não funcionaram como eu esperava.', 'Gustavo · Empreendedor', 'negative'),
(17, 'Plataforma completa, encontrei tudo que precisava em um só lugar.', 'Amanda · MEI', 'positive'),
(18, 'O processo de inscrição em cursos é confuso.', 'Felipe · Estudante', 'negative'),
(19, 'Bom no geral, mas a versão mobile precisa de ajustes.', 'Mariana · Usuário recorrente', 'warning'),
(20, 'Recomendo para todos os empreendedores iniciantes.', 'Diego · Iniciante', 'positive');

-- ── Effort Score ────────────────────────────────
INSERT INTO effort_score (id, label, percentual, cor, ordem) VALUES
(1, 'Muito fácil',  22, 'green',       1),
(2, 'Fácil',        32, 'light-green', 2),
(3, 'Neutro',       36, 'yellow',      3),
(4, 'Difícil',      22, 'orange',      4),
(5, 'Muito difícil',12, 'red',         5);

-- ── Heatmap Blocos (6 seções x 10 blocos) ───────
INSERT INTO heatmap_bloco (id, secao, posicao, nome, intensidade) VALUES
-- Navegação
(1,  'Navegação', 1,  'Logo SEBRAE', 'bg-baixo'),
(2,  'Navegação', 2,  '', 'bg-baixo'),
(3,  'Navegação', 3,  '', 'bg-empty'),
(4,  'Navegação', 4,  'Cursos', 'bg-alto'),
(5,  'Navegação', 5,  'Ferramentas', 'bg-alto'),
(6,  'Navegação', 6,  'Consultorias', 'bg-medio'),
(7,  'Navegação', 7,  'SEBRAE-SP', 'bg-medio'),
(8,  'Navegação', 8,  '', 'bg-empty'),
(9,  'Navegação', 9,  'Busca', 'bg-medio'),
(10, 'Navegação', 10, '', 'bg-alto'),
-- Hero
(11, 'Hero', 1,  '', 'bg-empty'),
(12, 'Hero', 2,  '', 'bg-empty'),
(13, 'Hero', 3,  '', 'bg-empty'),
(14, 'Hero', 4,  'CTA Principal', 'bg-baixo'),
(15, 'Hero', 5,  '', 'bg-medio'),
(16, 'Hero', 6,  '', 'bg-medio'),
(17, 'Hero', 7,  'Imagem Hero', 'bg-muito-baixo'),
(18, 'Hero', 8,  '', 'bg-muito-baixo'),
(19, 'Hero', 9,  '', 'bg-muito-baixo'),
(20, 'Hero', 10, '', 'bg-muito-baixo'),
-- Acesso Rápido
(21, 'Acesso Rápido', 1,  'Portal MEI', 'bg-medio'),
(22, 'Acesso Rápido', 2,  '', 'bg-alto'),
(23, 'Acesso Rápido', 3,  'Cursos Gratuitos', 'bg-alto'),
(24, 'Acesso Rápido', 4,  '', 'bg-alto'),
(25, 'Acesso Rápido', 5,  'Capital de Giro', 'bg-baixo'),
(26, 'Acesso Rápido', 6,  '', 'bg-medio'),
(27, 'Acesso Rápido', 7,  'Consultoria', 'bg-baixo'),
(28, 'Acesso Rápido', 8,  '', 'bg-baixo'),
(29, 'Acesso Rápido', 9,  'Eventos', 'bg-baixo'),
(30, 'Acesso Rápido', 10, '', 'bg-baixo'),
-- Conteúdo
(31, 'Conteúdo', 1,  'Notícia Destaque', 'bg-muito-baixo'),
(32, 'Conteúdo', 2,  '', 'bg-baixo'),
(33, 'Conteúdo', 3,  '', 'bg-baixo'),
(34, 'Conteúdo', 4,  'Artigo', 'bg-muito-baixo'),
(35, 'Conteúdo', 5,  '', 'bg-baixo'),
(36, 'Conteúdo', 6,  'Artigo', 'bg-muito-baixo'),
(37, 'Conteúdo', 7,  '', 'bg-muito-baixo'),
(38, 'Conteúdo', 8,  'Notícia', 'bg-muito-baixo'),
(39, 'Conteúdo', 9,  '', 'bg-muito-baixo'),
(40, 'Conteúdo', 10, '', 'bg-muito-baixo'),
-- Banner
(41, 'Banner', 1,  'Banner SEBRAETEC', 'bg-muito-baixo'),
(42, 'Banner', 2,  '', 'bg-muito-baixo'),
(43, 'Banner', 3,  '', 'bg-muito-baixo'),
(44, 'Banner', 4,  '', 'bg-muito-baixo'),
(45, 'Banner', 5,  '', 'bg-baixo'),
(46, 'Banner', 6,  '', 'bg-medio'),
(47, 'Banner', 7,  '', 'bg-baixo'),
(48, 'Banner', 8,  '', 'bg-muito-baixo'),
(49, 'Banner', 9,  '', 'bg-muito-baixo'),
(50, 'Banner', 10, '', 'bg-muito-baixo'),
-- Rodapé
(51, 'Rodapé', 1,  'Links Úteis', 'bg-muito-baixo'),
(52, 'Rodapé', 2,  '', 'bg-muito-baixo'),
(53, 'Rodapé', 3,  '', 'bg-muito-baixo'),
(54, 'Rodapé', 4,  '', 'bg-muito-baixo'),
(55, 'Rodapé', 5,  '', 'bg-muito-baixo'),
(56, 'Rodapé', 6,  '', 'bg-muito-baixo'),
(57, 'Rodapé', 7,  '', 'bg-muito-baixo'),
(58, 'Rodapé', 8,  'Redes Sociais', 'bg-muito-baixo'),
(59, 'Rodapé', 9,  '', 'bg-muito-baixo'),
(60, 'Rodapé', 10, '', 'bg-muito-baixo');

-- ── Scroll Profundidade (8 registros: 4 chart + 4 análise) ──
INSERT INTO scroll_profundidade (id, faixa, titulo, percentual, cor, ordem) VALUES
(1, '0-25%',   'Topo da página',        100, 'fill-blue',   1),
(2, '25-50%',  'Conteúdo principal',     68, 'fill-green',  2),
(3, '50-75%',  'Seção secundária',       41, 'fill-orange', 3),
(4, '75-100%', 'Rodapé',                 22, 'fill-red',    4);

-- ── Insights Automáticos ────────────────────────
INSERT INTO insight_automatico (id, tipo, badge, badge_cor, titulo, descricao, link, ordem) VALUES
(1, 'critical', '⚠ CRÍTICO  -12.3%', 'red', 'Queda crítica no engajamento de cursos', 'Taxa de engajamento em Cursos Online caiu 12,3% nos últimos 3 dias. Taxa de abandono subiu de 34% para 46%. Pico de rage clicks detectado na página de inscrição.', 'Ver heatmap →', 1),
(2, 'positive', '✓ POSITIVO  +23% retorno', 'green', 'Segmento MEI com alta retenção', 'Usuários MEI que acessam "Gestão Financeira" retornam 23% mais que a média do segmento. Padrão consistente nas últimas 2 semanas.', 'Explorar segmento →', 2),
(3, 'info', 'ℹ INSIGHT  +18% conversão', 'blue', 'Padrão de horário de alto valor', 'Picos de acesso às 10h–11h e 15h–16h apresentam +18% de conversão. Oportunidade para notificações segmentadas e conteúdo programado.', 'Ver relatório →', 3);

-- ── KPIs Principais ─────────────────────────────
INSERT INTO kpi_principal (id, label, valor, unidade, descricao, tendencia, trend_direction, icone_bg, icone_cor, icone_tipo, ordem) VALUES
(1, 'Taxa de Engajamento',     68.4, '%',   'Usuários que interagiram ativamente', 8.2,  'up',   '#eff6ff', '#3b82f6', 'eye',    1),
(2, 'Score de Esforço (CES)',  2.8,  'pts', 'Escala 1–5 · quanto menor, melhor',  -12.5, 'down', '#faf5ff', '#7c3aed', 'target', 2),
(3, 'Taxa de Retorno',         42.1, '%',   'Usuários que retornaram em 30 dias',  9.6,  'up',   '#f0fdf4', '#10b981', 'refresh',3),
(4, 'Tempo Médio de Sessão',   4.3,  'min', 'Média por sessão no período',         22.2, 'up',   '#fffbeb', '#f59e0b', 'clock',  4),
(5, 'Sessões Totais',          142.5,'k',   'Total de sessões no período',         11.1, 'up',   '#f0f9ff', '#0ea5e9', 'users',  5),
(6, 'Taxa de Conversão',       9.5,  '%',   'Cadastros / inscrições realizados',   15.9, 'up',   '#fdf4ff', '#a855f7', 'chart',  6);

-- ── Fontes de Tráfego ───────────────────────────
INSERT INTO fonte_trafego (id, label, percentual, cor, ordem) VALUES
(1, 'Orgânico', 45, '#10b981', 1),
(2, 'Direto',   23, '#3b82f6', 2),
(3, 'Social',   18, '#7c3aed', 3),
(4, 'E-mail',    9, '#f59e0b', 4),
(5, 'Pago',      5, '#ef4444', 5);

-- ── Evolução Temporal (26 dias) ─────────────────
INSERT INTO evolucao_temporal (id, dia, engajamento, retorno, volume, ordem) VALUES
(1,  '01/04', 65, 38, 4200, 1),
(2,  '',      72, 42, 3800, 2),
(3,  '',      60, 36, 4500, 3),
(4,  '',      68, 40, 3200, 4),
(5,  '',      63, 37, 1800, 5),
(6,  '06/04', 70, 44, 2400, 6),
(7,  '',      67, 39, 4100, 7),
(8,  '',      74, 43, 3600, 8),
(9,  '',      61, 35, 4800, 9),
(10, '',      66, 41, 3000, 10),
(11, '11/04', 64, 38, 1900, 11),
(12, '',      71, 45, 2600, 12),
(13, '',      68, 40, 4300, 13),
(14, '',      75, 42, 3700, 14),
(15, '',      62, 36, 4600, 15),
(16, '16/04', 69, 43, 3100, 16),
(17, '',      65, 39, 1700, 17),
(18, '',      73, 44, 2500, 18),
(19, '',      60, 37, 4000, 19),
(20, '',      68, 40, 3500, 20),
(21, '21/04', 66, 38, 4700, 21),
(22, '',      72, 43, 3200, 22),
(23, '',      64, 36, 1800, 23),
(24, '',      70, 41, 2400, 24),
(25, '',      67, 39, 4400, 25),
(26, '26/04', 76, 45, 4900, 26);
