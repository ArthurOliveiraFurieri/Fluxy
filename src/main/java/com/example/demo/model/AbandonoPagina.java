package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "abandono_pagina")
public class AbandonoPagina {

    @Id
    private Long id;
    private String nome;
    private int percentual;
    private String cor;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getPercentual() { return percentual; }
    public void setPercentual(int percentual) { this.percentual = percentual; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
}