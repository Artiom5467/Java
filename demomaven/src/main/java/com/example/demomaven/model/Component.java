package com.example.demomaven.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Component {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String type; // CPU, GPU, RAM, тощо
    private int powerConsumption; // у ватах
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;
    
    @OneToMany(mappedBy = "component")
    private List<BuildComponent> builds = new ArrayList<>();
    
    // Конструктор без id для створення нових компонентів
    public Component(String name, String type, int powerConsumption, String description) {
        this.name = name;
        this.type = type;
        this.powerConsumption = powerConsumption;
        this.description = description;
    }
    
    // Конструктор з виробником
    public Component(String name, String type, int powerConsumption, String description, Manufacturer manufacturer) {
        this.name = name;
        this.type = type;
        this.powerConsumption = powerConsumption;
        this.description = description;
        this.manufacturer = manufacturer;
    }
}