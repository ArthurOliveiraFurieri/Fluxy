package com.example.demo.repository;

import com.example.demo.model.ScrollProfundidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScrollProfundidadeRepository extends JpaRepository<ScrollProfundidade, Long> {
    List<ScrollProfundidade> findAllByOrderByOrdemAsc();
}
