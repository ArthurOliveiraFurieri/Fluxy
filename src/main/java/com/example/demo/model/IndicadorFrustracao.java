package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "indicador_frustracao")
public class IndicadorFrustracao {

    @Id
    private Long id;
    private String evento;
    private String pagina;
    private int ocorrencias;
    private String tendencia;
    private String severidade;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEvento() { return evento; }
    public void setEvento(String evento) { this.evento = evento; }
    public String getPagina() { return pagina; }
    public void setPagina(String pagina) { this.pagina = pagina; }
    public int getOcorrencias() { return ocorrencias; }
    public void setOcorrencias(int ocorrencias) { this.ocorrencias = ocorrencias; }
    public String getTendencia() { return tendencia; }
    public void setTendencia(String tendencia) { this.tendencia = tendencia; }
    public String getSeveridade() { return severidade; }
    public void setSeveridade(String severidade) { this.severidade = severidade; }
}