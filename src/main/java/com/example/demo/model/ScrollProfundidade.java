package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "scroll_profundidade")
public class ScrollProfundidade {

    @Id
    private Long id;
    private String faixa;
    private String titulo;
    private int percentual;
    private String cor;
    private int ordem;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFaixa() { return faixa; }
    public void setFaixa(String faixa) { this.faixa = faixa; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public int getPercentual() { return percentual; }
    public void setPercentual(int percentual) { this.percentual = percentual; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    public int getOrdem() { return ordem; }
    public void setOrdem(int ordem) { this.ordem = ordem; }
}
