package com.example.demomaven.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "component_id")
public class RAM extends Component {
    
    private Integer capacityGB;
    private Integer speed; // в МГц
    private String type; // DDR4, DDR5, etc.
    private Integer casLatency;
    private Integer modules; // кількість модулів у комплекті
    
    public RAM(String name, int powerConsumption, String description, 
              Integer capacityGB, Integer speed, String type, 
              Integer casLatency, Integer modules) {
        super(name, "RAM", powerConsumption, description);
        this.capacityGB = capacityGB;
        this.speed = speed;
        this.type = type;
        this.casLatency = casLatency;
        this.modules = modules;
    }
}