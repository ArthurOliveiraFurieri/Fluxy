package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
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

    public List<EngajamentoHora> getHoras() {
        List<EngajamentoHora> horas = horaRepo.findAll();
        horas.forEach(h -> {
            int variacao = random.nextInt(201) - 100;
            h.setValor(Math.max(100, h.getValor() + variacao));
        });
        return horas;
    }

    public List<ConteudoEngajamento> getConteudo() {
        List<ConteudoEngajamento> conteudos = conteudoRepo.findAll();
        conteudos.forEach(c -> {
            int variacao = random.nextInt(5) - 2;
            c.setPercentual(Math.max(1, Math.min(99, c.getPercentual() + variacao)));
        });
        return conteudos;
    }

    public List<RetencaoSegmento> getRetencao() {
        List<RetencaoSegmento> retencoes = retencaoRepo.findAll();
        retencoes.forEach(r -> {
            r.setSemana2(Math.max(1, Math.min(99, r.getSemana2() + random.nextInt(5) - 2)));
            r.setSemana3(Math.max(1, Math.min(99, r.getSemana3() + random.nextInt(5) - 2)));
            r.setSemana4(Math.max(1, Math.min(99, r.getSemana4() + random.nextInt(5) - 2)));
        });
        return retencoes;
    }

    public List<RadarSegmento> getRadar() {
        List<RadarSegmento> radares = radarRepo.findAll();
        radares.forEach(r -> {
            r.setVal1(clamp(r.getVal1() + random.nextInt(5) - 2));
            r.setVal2(clamp(r.getVal2() + random.nextInt(5) - 2));
            r.setVal3(clamp(r.getVal3() + random.nextInt(5) - 2));
            r.setVal4(clamp(r.getVal4() + random.nextInt(5) - 2));
            r.setVal5(clamp(r.getVal5() + random.nextInt(5) - 2));
            r.setVal6(clamp(r.getVal6() + random.nextInt(5) - 2));
        });
        return radares;
    }

    public List<Segmento> getSegmentos() {
        List<Segmento> segmentos = segmentoRepo.findAll();
        segmentos.forEach(s -> {
            s.setEngajamento(clamp(s.getEngajamento() + random.nextInt(5) - 2));
            s.setRetorno(clamp(s.getRetorno() + random.nextInt(5) - 2));
            int varSessoes = random.nextInt(501) - 250;
            s.setSessoes(Math.max(0, s.getSessoes() + varSessoes));
        });
        return segmentos;
    }

    private int clamp(int v) {
        return Math.max(0, Math.min(100, v));
    }
}
