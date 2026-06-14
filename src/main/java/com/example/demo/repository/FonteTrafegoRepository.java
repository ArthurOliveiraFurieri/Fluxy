package com.example.demo.repository;

import com.example.demo.model.FonteTrafego;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FonteTrafegoRepository extends JpaRepository<FonteTrafego, Long> {
    List<FonteTrafego> findAllByOrderByOrdemAsc();
}
