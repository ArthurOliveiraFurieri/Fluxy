package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "segmento")
public class Segmento {

    @Id
    private Long id;
    private String nome;
    private String cor;
    private int pct;
    private int engajamento;
    private int retorno;
    private int sessoes;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    public int getPct() { return pct; }
    public void setPct(int pct) { this.pct = pct; }
    public int getEngajamento() { return engajamento; }
    public void setEngajamento(int engajamento) { this.engajamento = engajamento; }
    public int getRetorno() { return retorno; }
    public void setRetorno(int retorno) { this.retorno = retorno; }
    public int getSessoes() { return sessoes; }
    public void setSessoes(int sessoes) { this.sessoes = sessoes; }
}
