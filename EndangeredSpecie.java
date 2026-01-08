package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="EndangeredSpecie")
public class EndangeredSpecie {
    @Id
    @Column(name="specie_name", nullable=false)
    private String specie_name;

    @Column(name="population")
    private int population;

    @Column(name="habitat")
    private String habitat;

    @ManyToOne
    @JoinColumn(name = "map_region_country", referencedColumnName = "country")
    private MapRegion map_region_country;

}