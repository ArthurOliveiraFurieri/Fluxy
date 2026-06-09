package com.example.demo.service;

import com.example.demo.model.NpsEvolucao;
import com.example.demo.repository.NpsEvolucaoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class FeedbackService {

    private final NpsEvolucaoRepository npsRepo;
    private final Random random = new Random();

    private List<NpsEvolucao> npsCache;

    public FeedbackService(NpsEvolucaoRepository npsRepo) {
        this.npsRepo = npsRepo;
    }

    @PostConstruct
    public void init() {
        regenerar();
    }

    public void regenerar() {
        npsCache = npsRepo.findAll();
        npsCache.forEach(n -> {
            int variacao = random.nextInt(5) - 2;
            n.setValor(Math.max(0, Math.min(100, n.getValor() + variacao)));
        });
    }

    public List<NpsEvolucao> getNps() {
        return npsCache;
    }
}