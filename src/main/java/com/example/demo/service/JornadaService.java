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

    private List<EtapaFunil> funilCache;
    private List<EventoCritico> eventosCache;
    private List<AbandonoPagina> abandonoCache;
    private List<MicroFeedback> feedbackCache;

    public JornadaService(EtapaFunilRepository etapaRepo,
                          EventoCriticoRepository eventoRepo,
                          AbandonoPaginaRepository abandonoRepo,
                          MicroFeedbackRepository feedbackRepo) {
        this.etapaRepo    = etapaRepo;
        this.eventoRepo   = eventoRepo;
        this.abandonoRepo = abandonoRepo;
        this.feedbackRepo = feedbackRepo;
    }

    public void regenerar() {
        funilCache = etapaRepo.findAll();
        funilCache.forEach(e -> {
            int variacao = random.nextInt(5) - 2;
            e.setPercentual(Math.max(1, Math.min(100, e.getPercentual() + variacao)));
            int varUsers = (int)(e.getUsuarios() * (random.nextDouble() * 0.04 - 0.02));
            e.setUsuarios(e.getUsuarios() + varUsers);
        });

        eventosCache = eventoRepo.findAll();
        eventosCache.forEach(e -> {
            int variacao = (int)(e.getUsuariosAfetados() * (random.nextDouble() * 0.04 - 0.02));
            e.setUsuariosAfetados(Math.max(0, e.getUsuariosAfetados() + variacao));
        });

        abandonoCache = abandonoRepo.findAll();
        abandonoCache.forEach(p -> {
            int variacao = random.nextInt(5) - 2;
            p.setPercentual(Math.max(1, Math.min(99, p.getPercentual() + variacao)));
        });

        feedbackCache = feedbackRepo.findAll();
        feedbackCache.forEach(f -> {
            f.setPositivo(Math.max(1, Math.min(99, f.getPositivo() + random.nextInt(5) - 2)));
            f.setNegativo(Math.max(1, Math.min(99, f.getNegativo() + random.nextInt(5) - 2)));
            f.setNeutro(Math.max(1, Math.min(99, f.getNeutro() + random.nextInt(5) - 2)));
        });
    }

    public List<EtapaFunil> getFunil() {
        if (funilCache == null) regenerar();
        return funilCache;
    }

    public List<EventoCritico> getEventos() {
        if (eventosCache == null) regenerar();
        return eventosCache;
    }

    public List<AbandonoPagina> getAbandono() {
        if (abandonoCache == null) regenerar();
        return abandonoCache;
    }

    public List<MicroFeedback> getFeedback() {
        if (feedbackCache == null) regenerar();
        return feedbackCache;
    }
}