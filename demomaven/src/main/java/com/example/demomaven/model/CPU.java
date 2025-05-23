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
public class CPU extends Component {
    
    private Integer cores;
    private Integer threads;
    private Double baseClockGHz;
    private Double boostClockGHz;
    private String socket;
    private Integer cacheSize; // в МБ
    private Integer processNm; // техпроцес в нм
    
    public CPU(String name, int powerConsumption, String description, 
               Integer cores, Integer threads, Double baseClockGHz, 
               Double boostClockGHz, String socket, Integer cacheSize, Integer processNm) {
        super(name, "CPU", powerConsumption, description);
        this.cores = cores;
        this.threads = threads;
        this.baseClockGHz = baseClockGHz;
        this.boostClockGHz = boostClockGHz;
        this.socket = socket;
        this.cacheSize = cacheSize;
        this.processNm = processNm;
    }
}