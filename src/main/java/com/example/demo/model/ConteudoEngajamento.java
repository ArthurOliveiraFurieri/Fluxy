package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "conteudo_engajamento")
public class ConteudoEngajamento {

    @Id
    private Long id;
    private String label;
    private int percentual;
    private String cor;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public int getPercentual() { return percentual; }
    public void setPercentual(int percentual) { this.percentual = percentual; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
}