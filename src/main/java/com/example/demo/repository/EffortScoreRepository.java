package com.example.demo.repository;

import com.example.demo.model.EffortScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EffortScoreRepository extends JpaRepository<EffortScore, Long> {
    List<EffortScore> findAllByOrderByOrdemAsc();
}
