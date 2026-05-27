package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "heatmap_bloco")
public class HeatmapBloco {

    @Id
    private Long id;
    private String secao;
    private int posicao;
    private String nome;
    private String intensidade;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSecao() { return secao; }
    public void setSecao(String secao) { this.secao = secao; }
    public int getPosicao() { return posicao; }
    public void setPosicao(int posicao) { this.posicao = posicao; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getIntensidade() { return intensidade; }
    public void setIntensidade(String intensidade) { this.intensidade = intensidade; }
}