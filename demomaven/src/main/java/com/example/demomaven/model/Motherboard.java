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
public class Motherboard extends Component {
    
    private String chipset;
    private String socket;
    private String formFactor; // ATX, micro-ATX, mini-ITX, etc.
    private Integer memorySlots;
    private Integer maxMemory; // в ГБ
    
    public Motherboard(String name, int powerConsumption, String description, 
                      String chipset, String socket, String formFactor, 
                      Integer memorySlots, Integer maxMemory) {
        super(name, "Motherboard", powerConsumption, description);
        this.chipset = chipset;
        this.socket = socket;
        this.formFactor = formFactor;
        this.memorySlots = memorySlots;
        this.maxMemory = maxMemory;
    }
}