package com.example.demo.controller;

import com.example.demo.service.JornadaService;
import com.example.demo.service.MetricasService;
import com.example.demo.service.HeatmapService;
import com.example.demo.service.FeedbackService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final JornadaService jornadaService;
    private final MetricasService metricasService;
    private final HeatmapService heatmapService;
    private final FeedbackService feedbackService;

    public ApiController(JornadaService jornadaService,
                         MetricasService metricasService,
                         HeatmapService heatmapService,
                         FeedbackService feedbackService) {
        this.jornadaService  = jornadaService;
        this.metricasService = metricasService;
        this.heatmapService  = heatmapService;
        this.feedbackService = feedbackService;
    }

    // ── Jornada ──────────────────────────────────────
    @GetMapping("/funil")
    public Object getFunil() { return jornadaService.getFunil(); }

    @GetMapping("/eventos")
    public Object getEventos() { return jornadaService.getEventos(); }

    @GetMapping("/abandono")
    public Object getAbandono() { return jornadaService.getAbandono(); }

    @GetMapping("/feedback")
    public Object getFeedback() { return jornadaService.getFeedback(); }

    // ── Métricas ─────────────────────────────────────
    @GetMapping("/metricas/horas")
    public Object getHoras() { return metricasService.getHoras(); }

    @GetMapping("/metricas/conteudo")
    public Object getConteudo() { return metricasService.getConteudo(); }

    @GetMapping("/metricas/retencao")
    public Object getRetencao() { return metricasService.getRetencao(); }

    @GetMapping("/metricas/radar")
    public Object getRadar() { return metricasService.getRadar(); }

    @GetMapping("/metricas/segmentos")
    public Object getSegmentos() { return metricasService.getSegmentos(); }

    // ── Heatmap ──────────────────────────────────────
    @GetMapping("/heatmap/navegacao")
    public Object getBlocosNavegacao() { return heatmapService.getBlocosNavegacao(); }

    @GetMapping("/heatmap/hero")
    public Object getBlocosHero() { return heatmapService.getBlocosHero(); }

    @GetMapping("/heatmap/acesso-rapido")
    public Object getBlocosAcessoRapido() { return heatmapService.getBlocosAcessoRapido(); }

    @GetMapping("/heatmap/elementos")
    public Object getElementos() { return heatmapService.getElementosMaisClicados(); }

    @GetMapping("/heatmap/insights")
    public Object getInsights() { return heatmapService.getInsights(); }

    // ── Feedback / NPS ───────────────────────────────
    @GetMapping("/nps")
    public Object getNps() { return feedbackService.getNps(); }

    // ── Regenerar Tudo ───────────────────────────────
    @PostMapping("/regenerar")
    public Object regenerar() {
        jornadaService.regenerar();
        metricasService.regenerar();
        heatmapService.regenerar();
        feedbackService.regenerar();
        return java.util.Map.of("status", "ok");
    }
}