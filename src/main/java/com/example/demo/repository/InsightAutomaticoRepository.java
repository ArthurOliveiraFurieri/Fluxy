package com.example.demo.repository;

import com.example.demo.model.InsightAutomatico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InsightAutomaticoRepository extends JpaRepository<InsightAutomatico, Long> {
    List<InsightAutomatico> findAllByOrderByOrdemAsc();
}
