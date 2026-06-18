# Fluxy

Fluxy é um protótipo funcional desenvolvido para o projeto G14 2026.1, focado em analisar a experiência do usuário no contexto do SEBRAE. A proposta substitui a dependência de formulários extensos de satisfação por dados de navegação, microinterações e microfeedbacks contextuais, ajudando a entender a jornada do usuário de forma mais natural.

Este repositório implementa o **dashboard de visualização e análise dos dados simulados**. Os microfeedbacks fazem parte da proposta completa, mas não estão implementados aqui — eles seriam exibidos nos canais de contato com o usuário (como o site do SEBRAE), não dentro do dashboard. Aqui, eles aparecem apenas como uma possível fonte de dados a ser captada e exibida.

## Objetivo

Demonstrar como a própria navegação do usuário pode se transformar em dado analisável. Em vez de depender só de pesquisas longas pós-experiência, a solução usa sinais indiretos da jornada, como:

- cliques e páginas acessadas;
- tempo de permanência e abandono de etapas;
- retorno e recorrência do usuário;
- interação com conteúdos e pontos de maior engajamento.

Esses dados são organizados em visualizações filtráveis para ajudar a identificar padrões, fricções e oportunidades de melhoria.

## Contexto

Desenvolvido no Programa Voice of Customer, o projeto busca captar percepções dos usuários reduzindo a dependência de formulários longos e de baixa adesão.

**Antes:** o usuário precisa responder formulários extensos para comunicar sua experiência.
**Depois:** a própria navegação gera sinais de experiência, complementados por microperguntas quando necessário.

Essa lógica foi demonstrada em visualizações narrativas separadas do repositório principal, mostrando como as microperguntas apareceriam durante a navegação no site do SEBRAE. Aqui, o foco é o dashboard e a visualização dos dados simulados.

## Estrutura da solução

A solução completa tem três camadas:

### 1. Dados comportamentais
A navegação do usuário é tratada como fonte de dados simulados, incluindo interações com a interface, páginas com maior abandono, etapas do funil, horários de engajamento, conteúdos mais acessados, retenção e continuidade da jornada.

### 2. Microfeedbacks contextuais *(não implementados neste repositório)*
São pequenas perguntas exibidas durante a navegação (ex: avaliação rápida de uma página, indicação de facilidade/dificuldade), pensadas para complementar os dados comportamentais sem exigir formulários longos. Como seriam aplicados nos canais reais do SEBRAE, não fazem parte do código deste repositório — apenas foram representados nas visualizações narrativas. O dashboard apenas exibiria os dados já captados por essa camada.

### 3. Dashboard de visualização *(implementado aqui)*
Centraliza os dados simulados em telas analíticas, com as seguintes áreas:

- Visão Geral
- Jornada do Usuário
- Métricas de Engajamento
- Heatmaps e Comportamento
- Feedback do Usuário
- Filtros e Segmentações

## Visualizações narrativas

Além do dashboard, o projeto usou visualizações narrativas para explicar a transição entre formulários extensos e uma escuta mais integrada à navegação, mostrando como o comportamento vira dado e como as microperguntas apareceriam na jornada. Elas têm função explicativa e não fazem parte do código deste repositório.

[Visualização Narrativa](jornadasebrae.figma.site)

## O que este repositório implementa

**Implementado:**
- páginas e navegação do dashboard;
- front-end com templates Thymeleaf;
- back-end em Spring Boot;
- endpoints para alimentar gráficos;
- banco H2 com dados simulados;
- telas de jornada, métricas, heatmap, comportamento, feedback e segmentação.

**Não implementado:**
- coleta real de navegação em sites externos;
- exibição de microfeedbacks no site do SEBRAE;
- integração com canais reais do SEBRAE;
- autenticação real de usuários;
- conexão com bases de produção;
- mecanismos definitivos de consentimento, privacidade e segurança.

## Funcionalidades principais representadas

- Interações e comportamento do usuário via dados simulados;
- Continuidade e retorno na jornada;
- Indicadores de engajamento, esforço e fricção;
- Padrões de recorrência;
- Jornada, funil e eventos críticos;
- Heatmaps e elementos mais clicados;
- Filtros e segmentação de dados;
- Dados simulados de feedback como fonte de análise.

## Épicas do projeto

**Épica 1 — Compreensão da experiência baseada em comportamento**
Transforma a navegação em dados de experiência (interações, continuidade, esforço, engajamento, recorrência). Representada por dados simulados e visualizações analíticas, não por coleta real.

**Épica 2 — Visualização estratégica**
Centraliza os indicadores no dashboard (visão geral, jornada, métricas, heatmaps, feedbacks, segmentações). Esta é a principal camada implementada neste repositório.

**Épica 3 — Interface de escuta direta contextual**
Usa microfeedbacks para complementar os dados comportamentais com respostas rápidas. Faz parte da solução geral e foi demonstrada nas visualizações narrativas, mas não está implementada no código, pois sua aplicação ocorreria no site do SEBRAE. O dashboard apenas receberia e exibiria esses dados.

## Tecnologias utilizadas

Java 21 · Spring Boot · Maven · Spring Web MVC · Thymeleaf · Spring Data JPA · H2 Database · HTML · CSS · JavaScript

## Páginas do sistema

```txt
/                         Redireciona para a visão geral
/visao-geral              Dashboard principal
/jornada                  Jornada do usuário
/metricas-engajamento     Métricas de engajamento
/feedback-usuario         Feedback do usuário
/heatmap-e-comportamento  Heatmaps e comportamento
/segmentos                Filtros e segmentações
```

| Tela | Descrição |
|---|---|
| **Visão Geral** | Panorama geral do comportamento, com indicadores de engajamento, esforço, recorrência e satisfação. |
| **Jornada do Usuário** | Jornada em formato de funil/fluxo, com eventos críticos, abandono e continuidade. |
| **Métricas de Engajamento** | Análises detalhadas por conteúdo, horário, segmento ou padrão de uso. |
| **Feedback do Usuário** | Dados simulados de feedback e percepção do usuário — representa a visualização dos dados que seriam coletados em canais externos, não a coleta interativa em si. |
| **Heatmaps e Comportamento** | Padrões de clique, scroll e elementos mais acessados. |
| **Segmentos** | Filtros por tipo de usuário, período, origem ou comportamento. |

## Endpoints de API

```txt
/api/funil
/api/eventos
/api/abandono
/api/feedback
/api/metricas/horas
/api/metricas/conteudo
/api/metricas/retencao
/api/metricas/radar
/api/metricas/segmentos
```

Todos retornam dados simulados usados nas visualizações e gráficos do sistema.

## Como acessar o projeto

### Pelo deploy
[Dashboard Fluxy](https://fluxy-agyo.onrender.com/visao-geral)

### Localmente

**Pré-requisitos:** Java 21+, Git, Maven (opcional, já tem wrapper incluído).

```bash
git clone https://github.com/ArthurOliveiraFurieri/Fluxy.git
cd Fluxy
```

Linux/macOS:
```bash
./mvnw spring-boot:run
```

Windows:
```bash
mvnw.cmd spring-boot:run
```

Depois, acesse: `http://localhost:8080`

## Banco de dados

Usa H2 Database (em memória), com dados iniciais carregados de:
```txt
src/main/resources/data.sql
```

Isso permite simular métricas e registros de navegação sem depender de uma base real.

## Dados simulados

Os dados não representam uma base real do SEBRAE — servem para demonstrar comportamento de usuários, etapas da jornada, eventos críticos, abandono, engajamento, esforço, recorrência, segmentações e feedback.

## Status do projeto

Protótipo funcional em média fidelidade, desenvolvido como entrega acadêmica. Não representa uma solução final em produção; simula dados e fluxos para demonstrar como a navegação pode virar informação útil de análise.

## Desenvolvimento

| Ciclo | Foco |
|---|---|
| **1** | Compreensão da experiência baseada em comportamento — engajamento, esforço e recorrência. |
| **2** | Visualização estratégica — organização dos dados em páginas de dashboard. |
| **3** | Escuta direta contextual — microfeedbacks como complemento aos sinais indiretos (demonstrado nas visualizações narrativas, não implementado no repositório). |

## Equipe

Desenvolvido pelo grupo **Fluxy**, formado por estudantes do 3º período da CESAR School (Design e Ciência da Computação), com divisão entre prototipação, interface, front-end, back-end, simulação de dados, documentação e apresentação.

## Observações

Projeto desenvolvido para fins acadêmicos, funcionando como protótipo demonstrativo com dados simulados. Uma implementação real exigiria integração com bases reais, canais digitais do SEBRAE, regras de privacidade, consentimento, segurança da informação e critérios técnicos de produção — incluindo a coleta real de navegação e os microfeedbacks contextuais, que continuariam sendo apenas exibidos (não coletados) pelo dashboard.