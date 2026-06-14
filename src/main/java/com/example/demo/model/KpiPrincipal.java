package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "kpi_principal")
public class KpiPrincipal {

    @Id
    private Long id;
    private String label;
    private double valor;
    private String unidade;      
    @Column(length = 500)
    private String descricao;
    private double tendencia;    
    private String trendDirection; 
    private String iconeBg;      
    private String iconeCor;      
    private String iconeTipo;    
    private int ordem;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public String getUnidade() { return unidade; }
    public void setUnidade(String unidade) { this.unidade = unidade; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public double getTendencia() { return tendencia; }
    public void setTendencia(double tendencia) { this.tendencia = tendencia; }
    public String getTrendDirection() { return trendDirection; }
    public void setTrendDirection(String trendDirection) { this.trendDirection = trendDirection; }
    public String getIconeBg() { return iconeBg; }
    public void setIconeBg(String iconeBg) { this.iconeBg = iconeBg; }
    public String getIconeCor() { return iconeCor; }
    public void setIconeCor(String iconeCor) { this.iconeCor = iconeCor; }
    public String getIconeTipo() { return iconeTipo; }
    public void setIconeTipo(String iconeTipo) { this.iconeTipo = iconeTipo; }
    public int getOrdem() { return ordem; }
    public void setOrdem(int ordem) { this.ordem = ordem; }
}
