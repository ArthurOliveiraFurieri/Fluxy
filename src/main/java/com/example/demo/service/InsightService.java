package com.example.demo.service;

import com.example.demo.model.InsightAutomatico;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class InsightService {

    private final Random random = new Random();

    // ===================== CRÍTICO =====================
    private final String[] criticoTitulos = {
        "Queda crítica no engajamento de cursos",
        "Aumento expressivo na taxa de abandono",
        "Quedas no acesso à plataforma nos últimos dias",
        "Engajamento em queda no módulo de Cursos Online",
        "Pico de saídas sem conclusão detectado"
    };

    private final String[] criticoDescricoes = {
        "Taxa de engajamento em Cursos Online caiu %d%% nos últimos 3 dias. Taxa de abandono subiu de 34%% para %d%%. Pico de rage clicks detectado na página de inscrição.",
        "Usuários estão abandonando o fluxo de inscrição %d%% mais que na semana anterior. Taxa de conclusão caiu para %d%%, exigindo atenção imediata.",
        "Detectamos queda de %d%% no tempo médio de sessão nos cursos online. A taxa de abandono na etapa de checkout chegou a %d%%.",
        "O módulo de Cursos Online registrou queda de %d%% nos acessos diários. Taxa de abandono na página de inscrição subiu para %d%%.",
        "Picos de rage clicks aumentaram %d%% na página de inscrição. Taxa de abandono geral atingiu %d%%, indicando possível falha de UX."
    };

    // ===================== POSITIVO =====================
    private final String[] positivoTitulos = {
        "Segmento MEI com alta retenção",
        "Crescimento consistente no segmento Autônomos",
        "Aumento de retorno em usuários Premium",
        "MEI lidera engajamento entre os segmentos",
        "Retenção em alta no público corporativo"
    };

    private final String[] positivoDescricoes = {
        "Usuários MEI que acessam \"Gestão Financeira\" retornam %d%% mais que a média do segmento. Padrão consistente nas últimas 2 semanas.",
        "Segmento Autônomos apresentou crescimento de %d%% na taxa de retorno, com destaque para o curso \"Gestão Financeira\".",
        "Usuários Premium tiveram aumento de %d%% no retorno semanal, mantendo padrão estável nas últimas semanas.",
        "O segmento MEI lidera engajamento com %d%% acima da média geral, principalmente em conteúdos de Gestão Financeira.",
        "Retenção do público corporativo subiu %d%% nas últimas 2 semanas, com forte adesão a trilhas de Gestão Financeira."
    };

    // ===================== INSIGHT =====================
    private final String[] insightTitulos = {
        "Padrão de horário de alto valor",
        "Janela de conversão identificada",
        "Novo padrão de acesso detectado",
        "Horários de pico com maior conversão",
        "Oportunidade de notificação segmentada"
    };

    private final String[] insightDescricoes = {
        "Picos de acesso às 10h–11h e 15h–16h apresentam +%d%% de conversão. Oportunidade para notificações segmentadas e conteúdo programado.",
        "Usuários acessam com %d%% mais frequência entre 10h e 11h. Recomenda-se programar conteúdos e notificações nesse horário.",
        "Identificado aumento de %d%% na conversão durante o período da tarde (15h–16h). Bom momento para campanhas direcionadas.",
        "Notificações enviadas entre 10h–11h tiveram %d%% mais engajamento que a média. Considere ajustar o agendamento de envios.",
        "Conversões no horário de pico (15h–16h) estão %d%% acima da média diária, indicando boa oportunidade para ofertas pontuais."
    };

    public List<InsightAutomatico> gerarInsights() {
        InsightAutomatico critico = new InsightAutomatico();
        critico.setId(1L);
        critico.setTipo("critical");
        int pctCritico = 8 + random.nextInt(10); 
        int pctAbandono = 40 + random.nextInt(15); 
        critico.setBadge("CRÍTICO -" + pctCritico + "%");
        critico.setBadgeCor("danger");
        int idxCritico = random.nextInt(criticoTitulos.length);
        critico.setTitulo(criticoTitulos[idxCritico]);
        critico.setDescricao(String.format(criticoDescricoes[idxCritico], pctCritico, pctAbandono));
        critico.setLink("Ver heatmap →");
        critico.setOrdem(1);

        InsightAutomatico positivo = new InsightAutomatico();
        positivo.setId(2L);
        positivo.setTipo("positive");
        int pctPositivo = 15 + random.nextInt(16); 
        positivo.setBadge("POSITIVO +" + pctPositivo + "% retorno");
        positivo.setBadgeCor("success");
        int idxPositivo = random.nextInt(positivoTitulos.length);
        positivo.setTitulo(positivoTitulos[idxPositivo]);

        positivo.setDescricao(String.format(positivoDescricoes[idxPositivo], pctPositivo));
        positivo.setLink("Explorar segmento →");
        positivo.setOrdem(2);

        InsightAutomatico insight = new InsightAutomatico();
        insight.setId(3L);
        insight.setTipo("info");
        int pctInsight = 10 + random.nextInt(16);
        insight.setBadge("INSIGHT +" + pctInsight + "% conversão");
        insight.setBadgeCor("info");
        int idxInsight = random.nextInt(insightTitulos.length);
        insight.setTitulo(insightTitulos[idxInsight]);
        insight.setDescricao(String.format(insightDescricoes[idxInsight], pctInsight));
        insight.setLink("Ver relatório →");
        insight.setOrdem(3);

        return List.of(critico, positivo, insight);
    }
}