package com.example.demo.repository;

import com.example.demo.model.EvolucaoTemporal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EvolucaoTemporalRepository extends JpaRepository<EvolucaoTemporal, Long> {
    List<EvolucaoTemporal> findAllByOrderByOrdemAsc();
}
