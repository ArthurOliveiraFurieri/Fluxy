package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String visaoGeral(Model model) {
        model.addAttribute("pageTitle",    "Visão Geral");
        model.addAttribute("pageSubtitle", "Panorama geral de engajamento e comportamento dos usuários");
        model.addAttribute("activePage",   "visao-geral");
        return "visao-geral";
    }
}
