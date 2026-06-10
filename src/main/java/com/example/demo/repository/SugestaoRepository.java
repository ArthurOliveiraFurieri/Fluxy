package com.example.demo.repository;

import com.example.demo.model.Sugestao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SugestaoRepository extends JpaRepository<Sugestao, Long> {
}