package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "evolucao_temporal")
public class EvolucaoTemporal {

    @Id
    private Long id;
    private String dia;        
    private int engajamento;
    private int retorno;
    private int volume;
    private int ordem;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDia() { return dia; }
    public void setDia(String dia) { this.dia = dia; }
    public int getEngajamento() { return engajamento; }
    public void setEngajamento(int engajamento) { this.engajamento = engajamento; }
    public int getRetorno() { return retorno; }
    public void setRetorno(int retorno) { this.retorno = retorno; }
    public int getVolume() { return volume; }
    public void setVolume(int volume) { this.volume = volume; }
    public int getOrdem() { return ordem; }
    public void setOrdem(int ordem) { this.ordem = ordem; }
}
