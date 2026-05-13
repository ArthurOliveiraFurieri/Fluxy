package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "nav_items")
public class NavItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;
    private String route;
    private String icon;
    private int    displayOrder;
    private boolean active;

    public NavItem() {}

    public NavItem(String label, String route, String icon, int displayOrder, boolean active) {
        this.label        = label;
        this.route        = route;
        this.icon         = icon;
        this.displayOrder = displayOrder;
        this.active       = active;
    }

    public Long getId()                             { return id; }
    public void setId(Long id)                      { this.id = id; }

    public String getLabel()                        { return label; }
    public void setLabel(String label)              { this.label = label; }

    public String getRoute()                        { return route; }
    public void setRoute(String route)              { this.route = route; }

    public String getIcon()                         { return icon; }
    public void setIcon(String icon)                { this.icon = icon; }

    public int getDisplayOrder()                    { return displayOrder; }
    public void setDisplayOrder(int displayOrder)   { this.displayOrder = displayOrder; }

    public boolean isActive()                       { return active; }
    public void setActive(boolean active)           { this.active = active; }
}