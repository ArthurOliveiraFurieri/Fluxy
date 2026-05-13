package com.example.demo.repository;

import com.example.demo.model.NavItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NavItemRepository extends JpaRepository<NavItem, Long> {

    List<NavItem> findAllByOrderByDisplayOrderAsc();
}