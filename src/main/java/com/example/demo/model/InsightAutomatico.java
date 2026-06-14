package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "insight_automatico")
public class InsightAutomatico {

    @Id
    private Long id;
    private String tipo;       
    private String badge;      
    private String badgeCor;    
    @Column(length = 1000)
    private String titulo;
    @Column(length = 2000)
    private String descricao;
    private String link;       
    private int ordem;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getBadge() { return badge; }
    public void setBadge(String badge) { this.badge = badge; }
    public String getBadgeCor() { return badgeCor; }
    public void setBadgeCor(String badgeCor) { this.badgeCor = badgeCor; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
    public int getOrdem() { return ordem; }
    public void setOrdem(int ordem) { this.ordem = ordem; }
}
