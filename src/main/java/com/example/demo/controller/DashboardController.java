package com.example.demo.controller;

import com.example.demo.service.HeatmapService;
import com.example.demo.service.JornadaService;
import com.example.demo.service.NavService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final NavService navService;
    private final HeatmapService heatmapService;
    private final JornadaService jornadaService;

    public DashboardController(
            NavService navService,
            HeatmapService heatmapService,
            JornadaService jornadaService
    ) {
        this.navService = navService;
        this.heatmapService = heatmapService;
        this.jornadaService = jornadaService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/visao-geral";
    }

    @GetMapping("/visao-geral")
    public String visaoGeral(Model model) {
        model.addAttribute("pageTitle", "Visão Geral");
        model.addAttribute("pageSubtitle", "Panorama geral de engajamento e comportamento dos usuários");
        model.addAttribute("activePage", "visao-geral");
        model.addAttribute("navItems", navService.getNavItemsForPage("visao-geral"));
        return "visao-geral";
    }

    @GetMapping("/jornada")
    public String jornadaUsuario(Model model) {
        model.addAttribute("pageTitle", "Jornada do Usuário");
        model.addAttribute("pageSubtitle", "Análise de funil, fluxo e eventos críticos na jornada");
        model.addAttribute("activePage", "jornada");
        model.addAttribute("navItems", navService.getNavItemsForPage("jornada"));

        model.addAttribute("funil", jornadaService.getFunil());
        model.addAttribute("eventosCriticos", jornadaService.getEventos());
        model.addAttribute("abandonoPaginas", jornadaService.getAbandono());
        model.addAttribute("microFeedbacks", jornadaService.getFeedback());

        return "jornada-usuario";
    }

    @GetMapping("/metricas-engajamento")
    public String metricasEngajamento(Model model) {
        model.addAttribute("pageTitle", "Métricas de Engajamento");
        model.addAttribute("pageSubtitle", "Análise detalhada do engajamento por segmento, conteúdo e horário");
        model.addAttribute("activePage", "metricas-engajamento");
        model.addAttribute("navItems", navService.getNavItemsForPage("metricas-engajamento"));
        return "metricas-engajamento";
    }

    @GetMapping("/feedback-usuario")
    public String feedbackUsuario(Model model) {
        model.addAttribute("pageTitle", "Feedback do Usuário");
        model.addAttribute("pageSubtitle", "Análise de satisfação, esforço e indicadores de frustração");
        model.addAttribute("activePage", "feedback-usuario");
        model.addAttribute("navItems", navService.getNavItemsForPage("feedback-usuario"));
        return "feedback-usuario";
    }

    @GetMapping("/heatmap-e-comportamento")
    public String heatmapEComportamento(Model model) {
        model.addAttribute("pageTitle", "Heatmaps e Comportamento");
        model.addAttribute("pageSubtitle", "Análise detalhada de cliques e profundidade de scroll");
        model.addAttribute("activePage", "heatmap-e-comportamento");
        model.addAttribute("navItems", navService.getNavItemsForPage("heatmap-e-comportamento"));

        model.addAttribute("blocosNavegacao", heatmapService.getBlocosNavegacao());
        model.addAttribute("blocosHero", heatmapService.getBlocosHero());
        model.addAttribute("blocosAcessoRapido", heatmapService.getBlocosAcessoRapido());
        model.addAttribute("elementosMaisClicados", heatmapService.getElementosMaisClicados());
        model.addAttribute("insights", heatmapService.getInsights());

        return "heatmap-e-comportamento";
    }
}