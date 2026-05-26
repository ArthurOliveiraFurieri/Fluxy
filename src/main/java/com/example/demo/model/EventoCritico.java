package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "evento_critico")
public class EventoCritico {

    @Id
    private Long id;
    private String nome;
    private String descricao;
    private int usuariosAfetados;
    private String severidade;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public int getUsuariosAfetados() { return usuariosAfetados; }
    public void setUsuariosAfetados(int usuariosAfetados) { this.usuariosAfetados = usuariosAfetados; }
    public String getSeveridade() { return severidade; }
    public void setSeveridade(String severidade) { this.severidade = severidade; }
}