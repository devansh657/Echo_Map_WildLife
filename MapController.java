package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RegionRequest;
import com.example.demo.model.MapRegion;
import com.example.demo.repository.MapRegionRepository;
import com.example.demo.service.MapService;
import com.example.demo.service.UserService;

import jakarta.websocket.server.PathParam;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/map")
public class MapController {
    private final MapService mapService;
    private final MapRegionRepository mapRepository;

    public MapController(MapService MapService, MapRegionRepository mapRepository) {
        this.mapService = MapService;
        this.mapRepository = mapRepository;
    }

   @PostMapping("/selectRegion")
public ResponseEntity<MapRegion> getMapRegionInfo(@RequestBody RegionRequest request) {
    String country = request.getCountry();
    Optional<MapRegion> data = mapService.getMapRegionInfo(country);
    return data.map(ResponseEntity::ok)
               .orElseGet(() -> ResponseEntity.notFound().build());
}

    @GetMapping("/searchRegion/{input}")
    public ResponseEntity<List<MapRegion>> searchRegion(@PathVariable String input){
        Optional<List<MapRegion>> regionList = mapService.searchMapRegion(input);
        return regionList.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
