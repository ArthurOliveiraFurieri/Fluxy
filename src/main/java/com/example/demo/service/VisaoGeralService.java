package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class VisaoGeralService {

    
    private final InsightService insightService;
    private final KpiPrincipalRepository kpiRepo;
    private final EvolucaoTemporalRepository evolucaoRepo;
    private final FonteTrafegoRepository fonteRepo;
    private final JornadaService jornadaService;
    private final Random random = new Random();

    private List<InsightAutomatico> insightCache;
    private List<KpiPrincipal> kpiCache;
    private List<EvolucaoTemporal> evolucaoCache;
    private List<FonteTrafego> fonteCache;

    public VisaoGeralService(InsightService insightService,
                        KpiPrincipalRepository kpiRepo,
                        EvolucaoTemporalRepository evolucaoRepo,
                        FonteTrafegoRepository fonteRepo,
                        JornadaService jornadaService) {
        this.insightService = insightService;
        this.kpiRepo        = kpiRepo;
        this.evolucaoRepo   = evolucaoRepo;
        this.fonteRepo      = fonteRepo;
        this.jornadaService = jornadaService;
    }

    public void regenerar() {
        insightCache = insightService.gerarInsights();

        kpiCache = kpiRepo.findAllByOrderByOrdemAsc();
        kpiCache.forEach(k -> {
            double varValor = k.getValor() * (random.nextDouble() * 0.06 - 0.03); 
            k.setValor(Math.round((k.getValor() + varValor) * 10.0) / 10.0);
            double novaTend = k.getTendencia() + (random.nextDouble() * 4 - 2);    
            k.setTendencia(Math.round(novaTend * 10.0) / 10.0);
            k.setTrendDirection(novaTend >= 0 ? "up" : "down");
        });

        evolucaoCache = evolucaoRepo.findAllByOrderByOrdemAsc();
        evolucaoCache.forEach(e -> {
            e.setEngajamento(clamp(e.getEngajamento() + random.nextInt(17) - 8, 0, 100));
            e.setRetorno(clamp(e.getRetorno() + random.nextInt(17) - 8, 0, 100));
            int varVol = (int)(e.getVolume() * (random.nextDouble() * 0.24 - 0.12));
            e.setVolume(Math.max(0, e.getVolume() + varVol));
        });

        fonteCache = fonteRepo.findAllByOrderByOrdemAsc();
        fonteCache.forEach(f -> {
            f.setPercentual(Math.max(1, Math.min(99, f.getPercentual() + random.nextInt(13) - 6)));
        });
    }

    public List<InsightAutomatico> getInsights() {
        if (insightCache == null) regenerar();
        return insightCache;
    }

    public List<KpiPrincipal> getKpis() {
        if (kpiCache == null) regenerar();
        return kpiCache;
    }

    public List<EvolucaoTemporal> getEvolucao() {
        if (evolucaoCache == null) regenerar();
        return evolucaoCache;
    }

    public List<FonteTrafego> getFontes() {
        if (fonteCache == null) regenerar();
        return fonteCache;
    }

    public List<EtapaFunil> getFunil() {
        return jornadaService.getFunil();
    }

    private int clamp(int v, int min, int max) {
        return Math.max(min, Math.min(max, v));
    }
}
