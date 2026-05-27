package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "micro_feedback")
public class MicroFeedback {

    @Id
    private Long id;
    private String etapa;
    private int positivo;
    private int negativo;
    private int neutro;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEtapa() { return etapa; }
    public void setEtapa(String etapa) { this.etapa = etapa; }
    public int getPositivo() { return positivo; }
    public void setPositivo(int positivo) { this.positivo = positivo; }
    public int getNegativo() { return negativo; }
    public void setNegativo(int negativo) { this.negativo = negativo; }
    public int getNeutro() { return neutro; }
    public void setNeutro(int neutro) { this.neutro = neutro; }
}
