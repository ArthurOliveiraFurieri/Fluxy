package com.example.demo.repository;

import com.example.demo.model.ElementoClicado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ElementoClicadoRepository extends JpaRepository<ElementoClicado, Long> {
    List<ElementoClicado> findAllByOrderByRankAsc();
}