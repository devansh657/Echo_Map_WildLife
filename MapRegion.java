package com.example.demo.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="MapRegion")
public class MapRegion {
    
    @Id
    @Column(name="country", nullable=false)
    private String country;

    @Column(name = "deforestation_rate")
    private double deforestation_rate;

    @OneToMany(mappedBy = "map_region_country") 
    private Set<EndangeredSpecie> species;

    public MapRegion() {
  
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getForest_cover_percent() {
        return forest_cover_percent;
    }

    public void setForest_cover_percent(double forest_cover_percent) {
        this.forest_cover_percent = forest_cover_percent;
    }

    public double getDeforestation_rate() {
        return deforestation_rate;
    }

    public void setDeforestation_rate(double deforestation_rate) {
        this.deforestation_rate = deforestation_rate;
    }

    public Set<EndangeredSpecie> getSpecies() {
        return species;
    }

    public void setSpecies(Set<EndangeredSpecie> species) {
        this.species = species;
    }

    @Column(name="forest_cover_percent")
    private double forest_cover_percent;

    public MapRegion(String country, double forest_cover_percent, double deforestation_rate,
            Set<EndangeredSpecie> species) {
        this.country = country;
        this.forest_cover_percent = forest_cover_percent;
        this.deforestation_rate = deforestation_rate;
        this.species = species;
    }

}
