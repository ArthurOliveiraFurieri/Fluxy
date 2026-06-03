package com.example.demo.service;

import com.example.demo.model.ElementoClicado;
import com.example.demo.model.HeatmapBloco;
import com.example.demo.model.InsightComportamento;
import com.example.demo.repository.ElementoClicadoRepository;
import com.example.demo.repository.HeatmapBlocoRepository;
import com.example.demo.repository.InsightComportamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeatmapService {

    private final HeatmapBlocoRepository heatmapBlocoRepository;
    private final ElementoClicadoRepository elementoClicadoRepository;
    private final InsightComportamentoRepository insightComportamentoRepository;

    public HeatmapService(
            HeatmapBlocoRepository heatmapBlocoRepository,
            ElementoClicadoRepository elementoClicadoRepository,
            InsightComportamentoRepository insightComportamentoRepository
    ) {
        this.heatmapBlocoRepository = heatmapBlocoRepository;
        this.elementoClicadoRepository = elementoClicadoRepository;
        this.insightComportamentoRepository = insightComportamentoRepository;
    }

    public List<HeatmapBloco> getBlocosNavegacao() {
        return heatmapBlocoRepository.findBySecaoOrderByPosicaoAsc("Navegação");
    }

    public List<HeatmapBloco> getBlocosHero() {
        return heatmapBlocoRepository.findBySecaoOrderByPosicaoAsc("Hero");
    }

    public List<HeatmapBloco> getBlocosAcessoRapido() {
        return heatmapBlocoRepository.findBySecaoOrderByPosicaoAsc("Acesso Rápido");
    }

    public List<ElementoClicado> getElementosMaisClicados() {
        return elementoClicadoRepository.findAllByOrderByRankAsc();
    }

    public List<InsightComportamento> getInsights() {
        return insightComportamentoRepository.findAll();
    }
}