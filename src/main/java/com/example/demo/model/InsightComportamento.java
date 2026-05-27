package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "insight_comportamento")
public class InsightComportamento {

    @Id
    private Long id;
    private String tipo;
    private String titulo;
    private String descricao;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}