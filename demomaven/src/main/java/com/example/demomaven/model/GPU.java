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
public class GPU extends Component {
    
    private Integer memoryGB;
    private String memoryType; // GDDR5, GDDR6, GDDR6X, etc.
    private Integer coreClock; // в МГц
    private Integer boostClock; // в МГц
    private String interfaceType; // PCIe 3.0, PCIe 4.0, etc.
    private Integer length; // в мм
    
    public GPU(String name, int powerConsumption, String description, 
              Integer memoryGB, String memoryType, Integer coreClock, 
              Integer boostClock, String interfaceType, Integer length) {
        super(name, "GPU", powerConsumption, description);
        this.memoryGB = memoryGB;
        this.memoryType = memoryType;
        this.coreClock = coreClock;
        this.boostClock = boostClock;
        this.interfaceType = interfaceType;
        this.length = length;
    }
}