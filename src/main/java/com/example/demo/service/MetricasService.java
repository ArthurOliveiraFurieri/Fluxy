package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class MetricasService {

    private final EngajamentoHoraRepository horaRepo;
    private final ConteudoEngajamentoRepository conteudoRepo;
    private final RetencaoSegmentoRepository retencaoRepo;
    private final RadarSegmentoRepository radarRepo;
    private final SegmentoRepository segmentoRepo;
    private final Random random = new Random();

    private List<EngajamentoHora> horasCache;
    private List<ConteudoEngajamento> conteudoCache;
    private List<RetencaoSegmento> retencaoCache;
    private List<RadarSegmento> radarCache;
    private List<Segmento> segmentosCache;

    public MetricasService(EngajamentoHoraRepository horaRepo,
                           ConteudoEngajamentoRepository conteudoRepo,
                           RetencaoSegmentoRepository retencaoRepo,
                           RadarSegmentoRepository radarRepo,
                           SegmentoRepository segmentoRepo) {
        this.horaRepo     = horaRepo;
        this.conteudoRepo = conteudoRepo;
        this.retencaoRepo = retencaoRepo;
        this.radarRepo    = radarRepo;
        this.segmentoRepo = segmentoRepo;
    }

    @PostConstruct
    public void init() {
        regenerar();
    }

    public void regenerar() {
        horasCache = horaRepo.findAll();
        horasCache.forEach(h -> {
            int variacao = (int)(h.getValor() * (random.nextDouble() * 0.04 - 0.02));
            h.setValor(Math.max(100, h.getValor() + variacao));
        });

        conteudoCache = conteudoRepo.findAll();
        conteudoCache.forEach(c -> {
            int variacao = random.nextInt(5) - 2;
            c.setPercentual(Math.max(1, Math.min(99, c.getPercentual() + variacao)));
        });

        retencaoCache = retencaoRepo.findAll();
        retencaoCache.forEach(r -> {
            r.setSemana2(clamp(r.getSemana2() + random.nextInt(5) - 2));
            r.setSemana3(clamp(r.getSemana3() + random.nextInt(5) - 2));
            r.setSemana4(clamp(r.getSemana4() + random.nextInt(5) - 2));
        });

        radarCache = radarRepo.findAll();
        radarCache.forEach(r -> {
            r.setVal1(clamp(r.getVal1() + random.nextInt(5) - 2));
            r.setVal2(clamp(r.getVal2() + random.nextInt(5) - 2));
            r.setVal3(clamp(r.getVal3() + random.nextInt(5) - 2));
            r.setVal4(clamp(r.getVal4() + random.nextInt(5) - 2));
            r.setVal5(clamp(r.getVal5() + random.nextInt(5) - 2));
            r.setVal6(clamp(r.getVal6() + random.nextInt(5) - 2));
        });

        segmentosCache = segmentoRepo.findAll();
        segmentosCache.forEach(s -> {
            s.setEngajamento(clamp(s.getEngajamento() + random.nextInt(5) - 2));
            s.setRetorno(clamp(s.getRetorno() + random.nextInt(5) - 2));
            int varSessoes = (int)(s.getSessoes() * (random.nextDouble() * 0.04 - 0.02));
            s.setSessoes(Math.max(0, s.getSessoes() + varSessoes));
        });
    }

    public List<EngajamentoHora> getHoras()         { return horasCache; }
    public List<ConteudoEngajamento> getConteudo()  { return conteudoCache; }
    public List<RetencaoSegmento> getRetencao()     { return retencaoCache; }
    public List<RadarSegmento> getRadar()           { return radarCache; }
    public List<Segmento> getSegmentos()            { return segmentosCache; }

    private int clamp(int v) {
        return Math.max(0, Math.min(100, v));
    }
}