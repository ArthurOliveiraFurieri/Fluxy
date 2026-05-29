package com.example.demo.controller;

import com.example.demo.service.JornadaService;
import com.example.demo.service.MetricasService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final JornadaService jornadaService;
    private final MetricasService metricasService;

    public ApiController(JornadaService jornadaService, MetricasService metricasService) {
        this.jornadaService  = jornadaService;
        this.metricasService = metricasService;
    }

    @GetMapping("/funil")
    public Object getFunil() {
        return jornadaService.getFunil();
    }

    @GetMapping("/eventos")
    public Object getEventos() {
        return jornadaService.getEventos();
    }

    @GetMapping("/abandono")
    public Object getAbandono() {
        return jornadaService.getAbandono();
    }

    @GetMapping("/feedback")
    public Object getFeedback() {
        return jornadaService.getFeedback();
    }

    @GetMapping("/metricas/horas")
    public Object getHoras() {
        return metricasService.getHoras();
    }

    @GetMapping("/metricas/conteudo")
    public Object getConteudo() {
        return metricasService.getConteudo();
    }

    @GetMapping("/metricas/retencao")
    public Object getRetencao() {
        return metricasService.getRetencao();
    }

    @GetMapping("/metricas/radar")
    public Object getRadar() {
        return metricasService.getRadar();
    }

    @GetMapping("/metricas/segmentos")
    public Object getSegmentos() {
        return metricasService.getSegmentos();
    }
}