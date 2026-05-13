package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SegmentosController {

    @GetMapping("/segmentos")
    public String abrirSegmentos(Model model) {
        // 'activePage' faz o ícone da sidebar ficar aceso (conforme seu código)
        model.addAttribute("activePage", "engajamento"); 
        model.addAttribute("pageTitle", "Filtros e Segmentações");
        model.addAttribute("pageSubtitle", "Configure segmentos avançados e compare comportamentos");
        
        return "segmentos"; // Nome do arquivo HTML em templates
    }
}