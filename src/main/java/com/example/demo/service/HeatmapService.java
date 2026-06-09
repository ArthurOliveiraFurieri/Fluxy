package com.example.demo.service;

import com.example.demo.model.ElementoClicado;
import com.example.demo.model.HeatmapBloco;
import com.example.demo.model.InsightComportamento;
import com.example.demo.repository.ElementoClicadoRepository;
import com.example.demo.repository.HeatmapBlocoRepository;
import com.example.demo.repository.InsightComportamentoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class HeatmapService {

    private final HeatmapBlocoRepository heatmapBlocoRepository;
    private final ElementoClicadoRepository elementoClicadoRepository;
    private final InsightComportamentoRepository insightComportamentoRepository;
    private final Random random = new Random();

    private List<HeatmapBloco> navegacaoCache;
    private List<HeatmapBloco> heroCache;
    private List<HeatmapBloco> acessoRapidoCache;
    private List<ElementoClicado> elementosCache;
    private List<InsightComportamento> insightsCache;

    public HeatmapService(
            HeatmapBlocoRepository heatmapBlocoRepository,
            ElementoClicadoRepository elementoClicadoRepository,
            InsightComportamentoRepository insightComportamentoRepository
    ) {
        this.heatmapBlocoRepository = heatmapBlocoRepository;
        this.elementoClicadoRepository = elementoClicadoRepository;
        this.insightComportamentoRepository = insightComportamentoRepository;
    }

    @PostConstruct
    public void init() {
        regenerar();
    }

    public void regenerar() {
        navegacaoCache    = heatmapBlocoRepository.findBySecaoOrderByPosicaoAsc("Navegação");
        heroCache         = heatmapBlocoRepository.findBySecaoOrderByPosicaoAsc("Hero");
        acessoRapidoCache = heatmapBlocoRepository.findBySecaoOrderByPosicaoAsc("Acesso Rápido");
        insightsCache     = insightComportamentoRepository.findAll();

        elementosCache = elementoClicadoRepository.findAllByOrderByRankAsc();
        elementosCache.forEach(e -> {
            int variacao = random.nextInt(5) - 2;
            e.setPercentual(Math.max(1, Math.min(100, e.getPercentual() + variacao)));
        });
    }

    public List<HeatmapBloco> getBlocosNavegacao()    { return navegacaoCache; }
    public List<HeatmapBloco> getBlocosHero()         { return heroCache; }
    public List<HeatmapBloco> getBlocosAcessoRapido() { return acessoRapidoCache; }
    public List<ElementoClicado> getElementosMaisClicados() { return elementosCache; }
    public List<InsightComportamento> getInsights()   { return insightsCache; }
}