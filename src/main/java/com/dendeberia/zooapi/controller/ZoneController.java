package com.dendeberia.zooapi.controller;

import com.dendeberia.zooapi.dao.domain.Zone;
import com.dendeberia.zooapi.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/zones")
@RequiredArgsConstructor
public class ZoneController {

    private final ZoneService zoneService;

    @PostMapping
    public Zone save(){
        return zoneService.save(new Zone(null, null));
    }

    @GetMapping("/moreFeedNeeded")
    public ResponseEntity<? extends Zone> getMoreFeedNeededZone(){
        try {
            return ResponseEntity.ok(zoneService.findMoreFeedNeeded());
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/minimalAnimals")
    public ResponseEntity<? extends Zone> getMinimalAnimalsZone(){
        try {
            return ResponseEntity.ok(zoneService.findMinimalAnimalsZone());
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
