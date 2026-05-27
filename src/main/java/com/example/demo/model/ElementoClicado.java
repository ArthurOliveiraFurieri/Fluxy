package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "elemento_clicado")
public class ElementoClicado {

    @Id
    private Long id;
    private int rank;
    private String nome;
    private int percentual;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getRank() { return rank; }
    public void setRank(int rank) { this.rank = rank; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getPercentual() { return percentual; }
    public void setPercentual(int percentual) { this.percentual = percentual; }
}