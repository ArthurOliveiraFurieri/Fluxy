package com.example.demo.config;

import com.example.demo.model.NavItem;
import com.example.demo.repository.NavItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedNavItems(NavItemRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(new NavItem("Visão Geral",             "/visao-geral",          "grid",        1, true));
                repo.save(new NavItem("Métricas de Engajamento", "/metricas-engajamento", "bar-chart-2", 2, false));
                repo.save(new NavItem("Análise de Conteúdo",     "/analise-conteudo",     "file-text",   3, false));
                repo.save(new NavItem("Segmentos",               "/segmentos",            "users",       4, false));
                repo.save(new NavItem("Conversões",              "/conversoes",           "trending-up", 5, false));
                repo.save(new NavItem("Relatórios",              "/relatorios",           "clipboard",   6, false));
                repo.save(new NavItem("Configurações",           "/configuracoes",        "settings",    7, false));
            }
        };
    }
}