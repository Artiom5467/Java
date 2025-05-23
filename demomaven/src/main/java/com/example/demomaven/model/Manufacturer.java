package com.example.demomaven.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Manufacturer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String country;
    private String website;
    private Integer foundedYear;
    
    @OneToMany(mappedBy = "manufacturer")
    private List<Component> components = new ArrayList<>();
    
    public Manufacturer(String name, String country, String website, Integer foundedYear) {
        this.name = name;
        this.country = country;
        this.website = website;
        this.foundedYear = foundedYear;
    }
}