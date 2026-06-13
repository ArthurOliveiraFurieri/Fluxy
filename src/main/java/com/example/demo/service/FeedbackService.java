package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class FeedbackService {

    private final NpsEvolucaoRepository npsRepo;
    private final IndicadorFrustracaoRepository frustracaoRepo;
    private final DepoimentoRepository depoimentoRepo;
    private final SugestaoRepository sugestaoRepo;
    private final Random random = new Random();

    private final EffortScoreRepository effortRepo;
    private List<EffortScore> effortCache;
    private List<NpsEvolucao> npsCache;
    private List<IndicadorFrustracao> frustracaoCache;
    private List<Depoimento> depoimentoCache;
    private List<Sugestao> sugestaoCache;
    

    public FeedbackService(NpsEvolucaoRepository npsRepo,
                       IndicadorFrustracaoRepository frustracaoRepo,
                       DepoimentoRepository depoimentoRepo,
                       SugestaoRepository sugestaoRepo,
                       EffortScoreRepository effortRepo) {
        this.npsRepo        = npsRepo;
        this.frustracaoRepo = frustracaoRepo;
        this.depoimentoRepo = depoimentoRepo;
        this.sugestaoRepo   = sugestaoRepo;
        this.effortRepo     = effortRepo;
    }

    public void regenerar() {
        npsCache = npsRepo.findAll();
        npsCache.forEach(n -> {
            int variacao = random.nextInt(21) - 10; 
            n.setValor(Math.max(0, Math.min(100, n.getValor() + variacao)));
        });

        frustracaoCache = frustracaoRepo.findAll();
        frustracaoCache.forEach(f -> {
            int valorAntigo = f.getOcorrencias();

            int variacaoPercent = random.nextInt(31) - 15; 
            int novoValor = (int)(valorAntigo * (1 + variacaoPercent / 100.0));
            f.setOcorrencias(Math.max(0, novoValor));

            String sinal = variacaoPercent >= 0 ? "+" : "";
            f.setTendencia(sinal + variacaoPercent + "%");
            
            boolean ocorrenciasAltas = f.getOcorrencias() > 3000;
            boolean piorando         = variacaoPercent > 0;

            if (ocorrenciasAltas && piorando) {
                f.setSeveridade("critical");
            } else if (ocorrenciasAltas || piorando) {
                f.setSeveridade("warning");
            } else {
                f.setSeveridade("controlled");
            }
        });

        List<Depoimento> todosDepoimentos = depoimentoRepo.findAll();
        Collections.shuffle(todosDepoimentos);
        depoimentoCache = todosDepoimentos.stream().limit(4).toList();

        sugestaoCache = sugestaoRepo.findAll();

        effortCache = effortRepo.findAllByOrderByOrdemAsc();
        effortCache.forEach(e -> {
            int variacao = random.nextInt(5) - 2;
            e.setPercentual(Math.max(1, Math.min(99, e.getPercentual() + variacao)));
        });
    }

        public List<NpsEvolucao> getNps() {
            if (npsCache == null) regenerar();
            return npsCache;
    }

    public List<IndicadorFrustracao> getFrustracao() {
        if (frustracaoCache == null) regenerar();
        return frustracaoCache;
    }

    public List<Depoimento> getDepoimentos() {
        if (depoimentoCache == null) regenerar();
        return depoimentoCache;
    }

    public List<Sugestao> getSugestoes() {
        if (sugestaoCache == null) regenerar();
        return sugestaoCache;
    }

    public List<EffortScore> getEffort() {
        if (effortCache == null) regenerar();
        return effortCache;
    }
}