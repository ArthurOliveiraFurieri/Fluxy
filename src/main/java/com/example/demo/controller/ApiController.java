package com.example.demo.controller;

import com.example.demo.service.JornadaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final JornadaService jornadaService;

    public ApiController(JornadaService jornadaService) {
        this.jornadaService = jornadaService;
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
}