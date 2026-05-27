package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "segmento")
public class Segmento {

    @Id
    private Long id;
    private String nome;
    private String cor;
    private String pct;
    private String engajamento;
    private String retorno;
    private String sessoes;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    public String getPct() { return pct; }
    public void setPct(String pct) { this.pct = pct; }
    public String getEngajamento() { return engajamento; }
    public void setEngajamento(String engajamento) { this.engajamento = engajamento; }
    public String getRetorno() { return retorno; }
    public void setRetorno(String retorno) { this.retorno = retorno; }
    public String getSessoes() { return sessoes; }
    public void setSessoes(String sessoes) { this.sessoes = sessoes; }
}
