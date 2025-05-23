package com.example.demomaven.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComputerBuild {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String description;
    private LocalDateTime createdDate;
    private Integer totalPowerConsumption;
    private Double estimatedPrice;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @OneToMany(mappedBy = "build")
    private List<BuildComponent> components = new ArrayList<>();
    
    public ComputerBuild(String name, String description, User user) {
        this.name = name;
        this.description = description;
        this.user = user;
        this.createdDate = LocalDateTime.now();
        this.totalPowerConsumption = 0;
        this.estimatedPrice = 0.0;
    }
    
    // Метод для розрахунку загального енергоспоживання
    public void calculateTotalPowerConsumption() {
        this.totalPowerConsumption = components.stream()
                .mapToInt(bc -> bc.getComponent().getPowerConsumption() * bc.getQuantity())
                .sum();
    }
}