package com.example.demo.repository;

import com.example.demo.model.KpiPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface KpiPrincipalRepository extends JpaRepository<KpiPrincipal, Long> {
    List<KpiPrincipal> findAllByOrderByOrdemAsc();
}
