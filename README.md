
# Fluxy

Fluxy é um protótipo funcional desenvolvido para o projeto G14 2026.1, com foco na análise da experiência do usuário para o contexto do SEBRAE. A solução geral do projeto propõe uma alternativa à dependência de formulários extensos de satisfação, utilizando dados de navegação, microinterações e microfeedbacks contextuais para apoiar a leitura da jornada do usuário.

Neste repositório, o foco está no dashboard de visualização e análise dos dados simulados. Os microfeedbacks fazem parte da proposta completa da solução, mas não estão implementados diretamente neste repositório, pois eles seriam exibidos nos canais de contato com o usuário, como o site do SEBRAE, e não dentro do dashboard em si.

No dashboard, os microfeedbacks aparecem apenas como uma possível fonte de captação de dados. Ou seja, o painel não é o local onde o usuário responde às microperguntas. Ele é o ambiente onde os dados coletados por essas interações seriam centralizados, tratados e visualizados.

## Objetivo do projeto

O objetivo do Fluxy é demonstrar como a própria navegação do usuário pode se transformar em dado analisável.

Em vez de depender apenas de pesquisas longas após a experiência, a solução considera sinais indiretos da jornada, como:

- cliques;
- páginas acessadas;
- tempo de permanência;
- abandono de etapas;
- retorno do usuário;
- interação com conteúdos;
- pontos de maior engajamento;
- padrões de recorrência.

Esses dados são organizados em visualizações filtráveis para facilitar a identificação de padrões, fricções e oportunidades de melhoria na experiência.

## Contexto

O projeto foi desenvolvido no contexto do Programa Voice of Customer, considerando o desafio de captar percepções dos usuários com menor dependência de formulários longos e de baixa adesão.

A proposta geral parte da seguinte mudança de lógica:

**Antes:** o usuário precisa responder formulários extensos para comunicar sua experiência.

**Depois:** a própria navegação gera sinais de experiência, que podem ser complementados por microperguntas quando necessário.

Essa lógica foi explicada por meio de visualizações narrativas separadas do repositório principal. Nessas visualizações, foi possível demonstrar como as microperguntas apareceriam durante a navegação no site do SEBRAE. No código deste repositório, a implementação se concentra no dashboard e na visualização dos dados simulados.

## Estrutura da solução geral

A solução completa do projeto é composta por três camadas principais:

### 1. Dados comportamentais

A navegação do usuário é tratada como fonte de dados. O sistema utiliza dados simulados para demonstrar como ações da jornada poderiam ser captadas e convertidas em indicadores de experiência.

Exemplos de dados considerados:

- interações com elementos da interface;
- páginas com maior abandono;
- etapas do funil de navegação;
- horários de maior engajamento;
- conteúdos mais acessados;
- segmentos com maior ou menor retenção;
- recorrência de acesso;
- continuidade da jornada.

### 2. Microfeedbacks contextuais

Os microfeedbacks fazem parte da proposta geral da solução, mas não estão implementados neste repositório.

A justificativa é que os microfeedbacks seriam aplicados no ambiente de navegação do usuário, como o site do SEBRAE, páginas de serviço, conteúdos digitais ou outros pontos de contato. Eles funcionariam como pequenas perguntas contextuais exibidas durante a experiência, em momentos nos quais uma resposta rápida pudesse complementar os dados comportamentais.

O dashboard não é o local de coleta dessas respostas. Ele é o local de visualização dos dados já captados. Por isso, neste repositório, os microfeedbacks são considerados apenas como uma das possíveis fontes de dados que alimentariam o painel, junto com dados de navegação, recorrência, abandono, engajamento e esforço.

Esses microfeedbacks foram representados nas visualizações narrativas do projeto para explicar como a coleta aconteceria na prática, mas não foram implementados no dashboard.

Exemplos de microfeedbacks possíveis:

- avaliação rápida de uma página;
- pergunta simples após uma etapa importante;
- indicação de facilidade ou dificuldade na navegação;
- reação contextual sobre um conteúdo acessado.

A função dessa camada é complementar os dados comportamentais com sinais explícitos do usuário, sem exigir formulários longos.

### 3. Dashboard de visualização

O dashboard é a parte implementada neste repositório. Ele centraliza os dados simulados em telas analíticas, permitindo que a equipe visualize padrões de comportamento e filtre informações por diferentes recortes.

As principais áreas do dashboard são:

- Visão Geral;
- Jornada do Usuário;
- Métricas de Engajamento;
- Heatmaps e Comportamento;
- Feedback do Usuário;
- Filtros e Segmentações.

## Visualizações narrativas

Além do dashboard implementado neste repositório, o projeto também utilizou visualizações narrativas para facilitar a explicação da solução.

Essas visualizações foram criadas para demonstrar a transição entre formulários extensos de satisfação e uma lógica de escuta mais integrada à navegação. Elas mostram como o comportamento do usuário poderia virar dado e como microperguntas poderiam aparecer em pontos específicos da jornada.

Essas visualizações têm função explicativa e não correspondem ao código principal deste repositório. Elas servem para representar como a coleta aconteceria nos canais de contato com o usuário, especialmente no site do SEBRAE.

[Visualização Narrativa](jornadasebrae.figma.site)

## O que este repositório implementa

Este repositório implementa o dashboard de análise e visualização dos dados simulados.

Estão presentes:

- páginas de visualização do dashboard;
- navegação entre seções analíticas;
- estrutura de front-end com templates Thymeleaf;
- lógica back-end em Spring Boot;
- endpoints para alimentar gráficos e visualizações;
- banco H2 com dados simulados;
- telas de jornada, métricas, heatmap, comportamento, feedback e segmentação.

Não estão implementados neste repositório:

- coleta real de navegação do usuário em sites externos;
- exibição de microfeedbacks contextuais no site do SEBRAE;
- integração com canais reais do SEBRAE;
- autenticação real de usuários;
- conexão com bases de dados de produção;
- mecanismos definitivos de consentimento, privacidade e segurança.

## Funcionalidades principais representadas

- Representar interações e comportamento do usuário por meio de dados simulados.
- Representar continuidade e retorno ao longo da jornada.
- Exibir indicadores de engajamento.
- Estimar esforço e fricção com base em comportamento simulado.
- Identificar padrões de recorrência.
- Exibir métricas da experiência em dashboard.
- Visualizar jornada, funil e eventos críticos.
- Exibir heatmaps e elementos mais clicados.
- Filtrar e segmentar dados.
- Visualizar dados simulados de feedback como uma das fontes de análise.

## Épicas do projeto

O protótipo foi estruturado a partir das principais histórias épicas definidas no backlog.

### Épica 1: Compreensão da experiência baseada em comportamento

Representa a transformação da navegação do usuário em dados de experiência, considerando interações, continuidade, esforço, engajamento e recorrência.

Funcionalidades relacionadas:

- registro de interações do usuário;
- registro de continuidade e retorno;
- geração de score de engajamento;
- estimativa de esforço do usuário;
- identificação de padrões de recorrência.

No repositório, essa épica é representada por dados simulados e visualizações analíticas, não por coleta real de navegação.

### Épica 2: Visualização estratégica

Centraliza os indicadores em um dashboard, com páginas específicas para visão geral, jornada, métricas, heatmaps, feedbacks e segmentações.

Funcionalidades relacionadas:

- dashboard de métricas da experiência;
- visualização da jornada do usuário;
- filtros e exploração de dados;
- análise de comportamento por segmento.

Esta é a principal camada implementada neste repositório.

### Épica 3: Interface de escuta direta contextual

Representa o uso de microfeedbacks para complementar os dados comportamentais com respostas rápidas do usuário.

Funcionalidades relacionadas:

- coleta de feedback rápido contextual;
- perguntas simples em pontos específicos da jornada;
- complemento entre dados explícitos e dados comportamentais.

Essa épica faz parte da solução geral e foi demonstrada nas visualizações narrativas. Ela não está implementada no código deste repositório porque sua aplicação ocorreria no site do SEBRAE ou em outros canais de interação com o usuário. O dashboard apenas receberia e exibiria os dados resultantes dessa captação.

## Tecnologias utilizadas

- Java 21
- Spring Boot
- Maven
- Spring Web MVC
- Thymeleaf
- Spring Data JPA
- H2 Database
- HTML
- CSS
- JavaScript

## Páginas do sistema

Após executar o projeto, as principais telas podem ser acessadas pelas seguintes rotas:

```txt
/                         Redireciona para a visão geral
/visao-geral              Dashboard principal
/jornada                  Jornada do usuário
/metricas-engajamento     Métricas de engajamento
/feedback-usuario         Feedback do usuário
/heatmap-e-comportamento  Heatmaps e comportamento
/segmentos                Filtros e segmentações
```

## Telas principais

### Visão Geral

Apresenta um panorama geral do comportamento dos usuários, reunindo indicadores principais de engajamento, esforço, recorrência e satisfação.

### Jornada do Usuário

Exibe a jornada em formato de funil, fluxo ou sequência de etapas, permitindo observar eventos críticos, abandono e continuidade.

### Métricas de Engajamento

Apresenta análises mais detalhadas sobre engajamento por conteúdo, horário, segmento ou padrão de uso.

### Feedback do Usuário

Organiza dados simulados relacionados ao feedback e à percepção do usuário. Essa tela não corresponde à implementação de microfeedbacks contextuais interativos no dashboard. Ela representa a visualização dos dados que poderiam ser coletados em canais externos, como o site do SEBRAE.

### Heatmaps e Comportamento

Demonstra padrões de clique, profundidade de scroll, áreas de maior interação e elementos mais acessados.

### Segmentos

Permite explorar os dados a partir de filtros e recortes, como tipo de usuário, período, origem do acesso ou comportamento identificado.

## Endpoints de API

O projeto possui endpoints usados para alimentar as visualizações do dashboard.

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

Esses endpoints retornam dados simulados utilizados nas visualizações e nos gráficos do sistema.

## Como acessar o projeto

O projeto pode ser acessado de duas formas: pela versão publicada em deploy ou executando localmente a aplicação.

### Acessar pelo deploy

Para visualizar o protótipo sem precisar instalar o projeto localmente, utilize o link abaixo:

[Dashboard Fluxy](https://fluxy-agyo.onrender.com/visao-geral)

### Executar localmente

Caso queira rodar o projeto na máquina, siga os passos abaixo.

#### Pré-requisitos

Antes de executar, é necessário ter instalado:

- Java 21 ou superior;
- Git;
- Maven, caso não utilize o Maven Wrapper incluído no projeto.

#### 1. Clonar o repositório

```bash
git clone https://github.com/ArthurOliveiraFurieri/Fluxy.git
cd Fluxy
```

#### 2. Executar com Maven Wrapper

No Linux ou macOS:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

#### 3. Acessar no navegador

Após iniciar a aplicação, acesse:

```txt
http://localhost:8080
```

## Banco de dados

O projeto utiliza H2 Database, um banco em memória usado para desenvolvimento e prototipação.

Os dados iniciais são carregados a partir do arquivo:

```txt
src/main/resources/data.sql
```

Essa abordagem permite simular métricas e registros de navegação sem depender de uma base real em produção.

## Dados simulados

Como o objetivo do protótipo é demonstrar a lógica da solução, os dados utilizados não representam uma base real do SEBRAE.

Os dados simulados servem para demonstrar:

- comportamento de usuários;
- etapas da jornada;
- eventos críticos;
- taxas de abandono;
- indicadores de engajamento;
- esforço percebido;
- padrões de recorrência;
- segmentações e filtros;
- visualizações analíticas de feedback.

## Status do projeto

Protótipo funcional em média fidelidade, desenvolvido como entrega acadêmica para demonstrar a proposta de solução do grupo Fluxy.

O sistema não representa uma solução final em produção. Ele simula dados e fluxos para demonstrar como a navegação do usuário pode ser convertida em informação útil para análise da experiência.

## Desenvolvimento

O desenvolvimento do projeto foi dividido em três ciclos, cada um relacionado a uma história épica.

### Ciclo 1

Foco na compreensão da experiência baseada em comportamento.

Neste ciclo, a solução passou a representar como ações do usuário podem gerar dados de engajamento, esforço e recorrência.

### Ciclo 2

Foco na visualização estratégica.

Neste ciclo, os dados foram organizados em páginas de dashboard, permitindo leitura analítica e navegação entre diferentes visualizações.

### Ciclo 3

Foco na escuta direta contextual.

Neste ciclo, a solução geral passou a considerar microfeedbacks rápidos como complemento aos sinais indiretos obtidos pela navegação. No entanto, essa camada foi demonstrada nas visualizações narrativas e não está implementada neste repositório, pois sua aplicação real ocorreria nos canais usados pelo usuário, e não no dashboard.

## Equipe

Projeto desenvolvido pelo grupo Fluxy, formado por estudantes do 3º período da CESAR School.

O grupo é composto por estudantes de Design e Ciência da Computação, com divisão de atividades entre:

- prototipação;
- interface;
- desenvolvimento front-end;
- desenvolvimento back-end;
- simulação de dados;
- documentação;
- apresentação da solução.

## Observações

Este projeto foi desenvolvido para fins acadêmicos.

A solução apresentada funciona como protótipo demonstrativo e utiliza dados simulados. Para uma implementação real, seria necessário integrar o sistema a bases de dados reais, canais digitais do SEBRAE, regras de privacidade, mecanismos de consentimento, segurança da informação e critérios técnicos de produção.

Também seria necessário implementar a coleta real de navegação e os microfeedbacks contextuais nos canais de interação com o usuário. O dashboard continuaria funcionando como ambiente de análise, não como ponto direto de coleta dessas respostas.
