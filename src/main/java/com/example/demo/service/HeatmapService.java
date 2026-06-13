package com.example.demo.service;

import com.example.demo.model.ElementoClicado;
import com.example.demo.model.HeatmapBloco;
import com.example.demo.model.InsightComportamento;
import com.example.demo.model.ScrollProfundidade;
import com.example.demo.repository.ElementoClicadoRepository;
import com.example.demo.repository.HeatmapBlocoRepository;
import com.example.demo.repository.InsightComportamentoRepository;
import com.example.demo.repository.ScrollProfundidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class HeatmapService {

    private final HeatmapBlocoRepository heatmapBlocoRepository;
    private final ElementoClicadoRepository elementoClicadoRepository;
    private final InsightComportamentoRepository insightComportamentoRepository;
    private final ScrollProfundidadeRepository scrollRepository;
    private final Random random = new Random();

    private List<HeatmapBloco> navegacaoCache;
    private List<HeatmapBloco> heroCache;
    private List<HeatmapBloco> acessoRapidoCache;
    private List<HeatmapBloco> conteudoCache;
    private List<HeatmapBloco> bannerCache;
    private List<HeatmapBloco> rodapeCache;
    private List<ElementoClicado> elementosCache;
    private List<InsightComportamento> insightsCache;
    private List<ScrollProfundidade> scrollCache;

    public HeatmapService(
            HeatmapBlocoRepository heatmapBlocoRepository,
            ElementoClicadoRepository elementoClicadoRepository,
            InsightComportamentoRepository insightComportamentoRepository,
            ScrollProfundidadeRepository scrollRepository
    ) {
        this.heatmapBlocoRepository = heatmapBlocoRepository;
        this.elementoClicadoRepository = elementoClicadoRepository;
        this.insightComportamentoRepository = insightComportamentoRepository;
        this.scrollRepository = scrollRepository;
    }

    private List<HeatmapBloco> embaralharIntensidades(List<HeatmapBloco> blocos) {
        List<String> intensidades = new java.util.ArrayList<>();
        for (HeatmapBloco b : blocos) {
            if (!"bg-empty".equals(b.getIntensidade())) {
                intensidades.add(b.getIntensidade());
            }
        }
        java.util.Collections.shuffle(intensidades);

        int i = 0;
        for (HeatmapBloco b : blocos) {
            if (!"bg-empty".equals(b.getIntensidade())) {
                b.setIntensidade(intensidades.get(i++));
            }
        }
        return blocos;
    }

    public void regenerar() {
        navegacaoCache    = embaralharIntensidades(heatmapBlocoRepository.findBySecaoOrderByPosicaoAsc("Navegação"));
        heroCache         = embaralharIntensidades(heatmapBlocoRepository.findBySecaoOrderByPosicaoAsc("Hero"));
        acessoRapidoCache = embaralharIntensidades(heatmapBlocoRepository.findBySecaoOrderByPosicaoAsc("Acesso Rápido"));
        conteudoCache     = embaralharIntensidades(heatmapBlocoRepository.findBySecaoOrderByPosicaoAsc("Conteúdo"));
        bannerCache       = embaralharIntensidades(heatmapBlocoRepository.findBySecaoOrderByPosicaoAsc("Banner"));
        rodapeCache       = embaralharIntensidades(heatmapBlocoRepository.findBySecaoOrderByPosicaoAsc("Rodapé"));
        insightsCache     = insightComportamentoRepository.findAll();

        elementosCache = elementoClicadoRepository.findAllByOrderByRankAsc();
        elementosCache.forEach(e -> {
            int variacao = random.nextInt(51) - 25; 
            e.setPercentual(Math.max(5, Math.min(100, e.getPercentual() + variacao)));
        });

        scrollCache = scrollRepository.findAllByOrderByOrdemAsc();
        scrollCache.forEach(s -> {
            int variacao = random.nextInt(51) - 25;
            s.setPercentual(Math.max(5, Math.min(100, s.getPercentual() + variacao)));
        });
    }

    public List<HeatmapBloco> getBlocosNavegacao()    { if (navegacaoCache == null) regenerar();    return navegacaoCache; }
    public List<HeatmapBloco> getBlocosHero()         { if (heroCache == null) regenerar();         return heroCache; }
    public List<HeatmapBloco> getBlocosAcessoRapido() { if (acessoRapidoCache == null) regenerar();  return acessoRapidoCache; }
    public List<HeatmapBloco> getBlocosConteudo()     { if (conteudoCache == null) regenerar();      return conteudoCache; }
    public List<HeatmapBloco> getBlocosBanner()       { if (bannerCache == null) regenerar();        return bannerCache; }
    public List<HeatmapBloco> getBlocosRodape()       { if (rodapeCache == null) regenerar();        return rodapeCache; }
    public List<ElementoClicado> getElementosMaisClicados() { if (elementosCache == null) regenerar(); return elementosCache; }
    public List<InsightComportamento> getInsights()   { if (insightsCache == null) regenerar();     return insightsCache; }
    public List<ScrollProfundidade> getScroll()       { if (scrollCache == null) regenerar();       return scrollCache; }
}
