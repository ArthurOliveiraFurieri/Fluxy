package com.example.demo.repository;

import com.example.demo.model.HeatmapBloco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeatmapBlocoRepository extends JpaRepository<HeatmapBloco, Long> {
    List<HeatmapBloco> findBySecaoOrderByPosicaoAsc(String secao);
}