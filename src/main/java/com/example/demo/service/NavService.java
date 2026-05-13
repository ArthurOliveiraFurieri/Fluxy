package com.example.demo.service;

import com.example.demo.model.NavItem;
import com.example.demo.repository.NavItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NavService {

    private final NavItemRepository repo;

    public NavService(NavItemRepository repo) {
        this.repo = repo;
    }

    public List<NavItem> getNavItems() {
        return repo.findAllByOrderByDisplayOrderAsc();
    }

    public List<NavItem> getNavItemsForPage(String activePage) {
        List<NavItem> items = getNavItems();
        items.forEach(item -> item.setActive(item.getRoute().equals("/" + activePage)));
        return items;
    }
}