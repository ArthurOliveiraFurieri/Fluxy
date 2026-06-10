package com.example.demo.service;

import com.example.demo.model.Segmento;
import com.example.demo.repository.SegmentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class SegmentosService {

    private final SegmentoRepository segmentoRepository;
    private final Random random = new Random();

    private List<Segmento> cache;

    public SegmentosService(SegmentoRepository segmentoRepository) {
        this.segmentoRepository = segmentoRepository;
    }

    public void regenerar() {

        cache = segmentoRepository.findAll();

        cache.forEach(s -> {

            int variacaoEng = random.nextInt(5) - 2;
            s.setEngajamento(
                Math.max(1,
                    Math.min(100,
                        s.getEngajamento() + variacaoEng)));

            int variacaoRet = random.nextInt(5) - 2;
            s.setRetorno(
                Math.max(1,
                    Math.min(100,
                        s.getRetorno() + variacaoRet)));

            int variacaoSessao =
                (int)(s.getSessoes() *
                    (random.nextDouble() * 0.04 - 0.02));

            s.setSessoes(
                s.getSessoes() + variacaoSessao);
        });
    }

    public List<Segmento> getSegmentos() {

        if (cache == null) {
            regenerar();
        }

        return cache;
    }
}