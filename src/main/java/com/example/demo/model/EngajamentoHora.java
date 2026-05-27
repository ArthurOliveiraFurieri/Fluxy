package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "engajamento_hora")
public class EngajamentoHora {

    @Id
    private Long id;
    private String hora;
    private int valor;
    private boolean peak;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }
    public int getValor() { return valor; }
    public void setValor(int valor) { this.valor = valor; }
    public boolean isPeak() { return peak; }
    public void setPeak(boolean peak) { this.peak = peak; }
}
