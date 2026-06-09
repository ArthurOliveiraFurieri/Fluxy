package com.example.demo.repository;

import com.example.demo.model.NpsEvolucao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NpsEvolucaoRepository extends JpaRepository<NpsEvolucao, Long> {
}