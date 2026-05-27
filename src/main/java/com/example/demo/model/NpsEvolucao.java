package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "nps_evolucao")
public class NpsEvolucao {

    @Id
    private Long id;
    private String mes;
    private int valor;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMes() { return mes; }
    public void setMes(String mes) { this.mes = mes; }
    public int getValor() { return valor; }
    public void setValor(int valor) { this.valor = valor; }
}