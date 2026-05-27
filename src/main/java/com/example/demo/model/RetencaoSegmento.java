package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "retencao_segmento")
public class RetencaoSegmento {

    @Id
    private Long id;
    private String label;
    private String cor;
    private int semana1;
    private int semana2;
    private int semana3;
    private int semana4;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    public int getSemana1() { return semana1; }
    public void setSemana1(int semana1) { this.semana1 = semana1; }
    public int getSemana2() { return semana2; }
    public void setSemana2(int semana2) { this.semana2 = semana2; }
    public int getSemana3() { return semana3; }
    public void setSemana3(int semana3) { this.semana3 = semana3; }
    public int getSemana4() { return semana4; }
    public void setSemana4(int semana4) { this.semana4 = semana4; }
}
