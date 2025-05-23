package com.example.demomaven.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildComponent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "build_id")
    private ComputerBuild build;
    
    @ManyToOne
    @JoinColumn(name = "component_id")
    private Component component;
    
    private Integer quantity;
    
    public BuildComponent(ComputerBuild build, Component component, Integer quantity) {
        this.build = build;
        this.component = component;
        this.quantity = quantity;
    }
}