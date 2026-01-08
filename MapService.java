package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.MapRegion;
import com.example.demo.repository.MapRegionRepository;
import com.example.demo.repository.PostRepository;

import jakarta.transaction.Transactional;

@Service
public class MapService {
    
    private final MapRegionRepository mapRepository;

    @Autowired
    public MapService(MapRegionRepository mapRepository) {
        this.mapRepository = mapRepository;
    }


    public Optional<MapRegion> getMapRegionInfo(String country){
        Optional<MapRegion> data = mapRepository.findByCountry(country);
        return data;
    }

    public Optional<List<MapRegion>> searchMapRegion(String input){
       return mapRepository.findByInput(input);

    }
}
