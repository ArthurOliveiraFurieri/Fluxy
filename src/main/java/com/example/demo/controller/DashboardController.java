package com.example.demo.controller;

import com.example.demo.service.NavService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final NavService navService;

    public DashboardController(NavService navService) {
        this.navService = navService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/visao-geral";
    }

    @GetMapping("/visao-geral")
    public String visaoGeral(Model model) {
        model.addAttribute("pageTitle",    "Visão Geral");
        model.addAttribute("pageSubtitle", "Panorama geral de engajamento e comportamento dos usuários");
        model.addAttribute("activePage",   "visao-geral");
        model.addAttribute("navItems",     navService.getNavItemsForPage("visao-geral"));
        return "visao-geral";
    }

    @GetMapping("/jornada")
    public String jornadaUsuario(Model model) {
        model.addAttribute("activePage", "jornada");
        return "jornada-usuario";
    }

    @GetMapping("/metricas-engajamento")
    public String metricasEngajamento(Model model) {
        model.addAttribute("pageTitle",    "Métricas de Engajamento");
        model.addAttribute("pageSubtitle", "Análise detalhada do engajamento por segmento, conteúdo e horário");
        model.addAttribute("activePage",   "metricas-engajamento");
        model.addAttribute("navItems",     navService.getNavItemsForPage("metricas-engajamento"));
        return "metricas-engajamento";
    }
}