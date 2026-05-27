package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "radar_segmento")
public class RadarSegmento {

    @Id
    private Long id;
    private String label;
    private String cor;
    private int val1;
    private int val2;
    private int val3;
    private int val4;
    private int val5;
    private int val6;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    public int getVal1() { return val1; }
    public void setVal1(int val1) { this.val1 = val1; }
    public int getVal2() { return val2; }
    public void setVal2(int val2) { this.val2 = val2; }
    public int getVal3() { return val3; }
    public void setVal3(int val3) { this.val3 = val3; }
    public int getVal4() { return val4; }
    public void setVal4(int val4) { this.val4 = val4; }
    public int getVal5() { return val5; }
    public void setVal5(int val5) { this.val5 = val5; }
    public int getVal6() { return val6; }
    public void setVal6(int val6) { this.val6 = val6; }
}