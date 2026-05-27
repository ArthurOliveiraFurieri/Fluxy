package com.example.demo.repository;

import com.example.demo.model.MicroFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MicroFeedbackRepository extends JpaRepository<MicroFeedback, Long> {
}