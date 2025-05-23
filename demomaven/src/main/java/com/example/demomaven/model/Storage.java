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
public class Storage extends Component {
    
    private Integer capacityGB;
    private String type; // SSD, HDD, etc.
    private String interfaceType; // SATA, NVMe, etc.
    private Integer readSpeed; // в МБ/с
    private Integer writeSpeed; // в МБ/с
    
    public Storage(String name, int powerConsumption, String description, 
                  Integer capacityGB, String type, String interfaceType, 
                  Integer readSpeed, Integer writeSpeed) {
        super(name, "Storage", powerConsumption, description);
        this.capacityGB = capacityGB;
        this.type = type;
        this.interfaceType = interfaceType;
        this.readSpeed = readSpeed;
        this.writeSpeed = writeSpeed;
    }
}