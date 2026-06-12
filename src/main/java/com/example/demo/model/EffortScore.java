package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "effort_score")
public class EffortScore {

    @Id
    private Long id;
    private String label;
    private int percentual;
    private String cor;
    private int ordem;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public int getPercentual() { return percentual; }
    public void setPercentual(int percentual) { this.percentual = percentual; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    public int getOrdem() { return ordem; }
    public void setOrdem(int ordem) { this.ordem = ordem; }
}
