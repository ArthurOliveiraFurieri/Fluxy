package com.example.demo.service;

import com.example.demo.model.NpsEvolucao;
import com.example.demo.repository.NpsEvolucaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class FeedbackService {

    private final NpsEvolucaoRepository npsRepo;
    private final Random random = new Random();

    public FeedbackService(NpsEvolucaoRepository npsRepo) {
        this.npsRepo = npsRepo;
    }

    public List<NpsEvolucao> getNps() {
        List<NpsEvolucao> lista = npsRepo.findAll();
        lista.forEach(n -> {
            int variacao = random.nextInt(5) - 2;
            n.setValor(Math.max(0, Math.min(100, n.getValor() + variacao)));
        });
        return lista;
    }
}