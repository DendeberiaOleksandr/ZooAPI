package com.dendeberia.zooapi.controller;

import com.dendeberia.zooapi.controller.dto.AnimalSaveDto;
import com.dendeberia.zooapi.dao.domain.Animal;
import com.dendeberia.zooapi.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @PostMapping
    public ResponseEntity<? extends Animal> save(@RequestBody @Valid Animal animal){
        return ResponseEntity.ok(animalService.save(animal));
    }

    @GetMapping
    public List<Animal> getAll(@RequestParam(required = false) Long zone,
                               @RequestParam(required = false) String name){
        return animalService.findAll(zone, name);
    }

}
