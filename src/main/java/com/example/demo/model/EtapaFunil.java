package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "etapa_funil")
public class EtapaFunil {

    @Id
    private Long id;
    private String nome;
    private int percentual;
    private int usuarios;
    private int dropPercentual;
    private int dropUsuarios;
    private String cor;
    private int ordem;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getPercentual() { return percentual; }
    public void setPercentual(int percentual) { this.percentual = percentual; }
    public int getUsuarios() { return usuarios; }
    public void setUsuarios(int usuarios) { this.usuarios = usuarios; }
    public int getDropPercentual() { return dropPercentual; }
    public void setDropPercentual(int dropPercentual) { this.dropPercentual = dropPercentual; }
    public int getDropUsuarios() { return dropUsuarios; }
    public void setDropUsuarios(int dropUsuarios) { this.dropUsuarios = dropUsuarios; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    public int getOrdem() { return ordem; }
    public void setOrdem(int ordem) { this.ordem = ordem; }
}
