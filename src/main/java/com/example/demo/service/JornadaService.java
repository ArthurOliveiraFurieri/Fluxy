package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class JornadaService {

    private final EtapaFunilRepository etapaRepo;
    private final EventoCriticoRepository eventoRepo;
    private final AbandonoPaginaRepository abandonoRepo;
    private final MicroFeedbackRepository feedbackRepo;
    private final Random random = new Random();

    public JornadaService(EtapaFunilRepository etapaRepo,
                          EventoCriticoRepository eventoRepo,
                          AbandonoPaginaRepository abandonoRepo,
                          MicroFeedbackRepository feedbackRepo) {
        this.etapaRepo   = etapaRepo;
        this.eventoRepo  = eventoRepo;
        this.abandonoRepo= abandonoRepo;
        this.feedbackRepo= feedbackRepo;
    }

    public List<EtapaFunil> getFunil() {
        List<EtapaFunil> etapas = etapaRepo.findAll();
        etapas.forEach(e -> {
            int variacao = random.nextInt(5) - 2; 
            e.setUsuarios(e.getUsuarios() + variacao * 100);
        });
        return etapas;
    }

    public List<EventoCritico> getEventos() {
        List<EventoCritico> eventos = eventoRepo.findAll();
        eventos.forEach(e -> {
            int variacao = random.nextInt(200) - 100; 
            e.setUsuariosAfetados(e.getUsuariosAfetados() + variacao);
        });
        return eventos;
    }

    public List<AbandonoPagina> getAbandono() {
        List<AbandonoPagina> paginas = abandonoRepo.findAll();
        paginas.forEach(p -> {
            int variacao = random.nextInt(3) - 1; 
            int novo = p.getPercentual() + variacao;
            p.setPercentual(Math.max(1, Math.min(99, novo)));
        });
        return paginas;
    }

    public List<MicroFeedback> getFeedback() {
        List<MicroFeedback> feedbacks = feedbackRepo.findAll();
        feedbacks.forEach(f -> {
            int variacao = random.nextInt(3) - 1;
            f.setPositivo(Math.max(1, f.getPositivo() + variacao));
            f.setNegativo(Math.max(1, f.getNegativo() + variacao));
        });
        return feedbacks;
    }
}