package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "depoimento")
public class Depoimento {

    @Id
    private Long id;
    private String texto;
    private String usuario;
    private String tipo;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
