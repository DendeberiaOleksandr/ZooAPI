package com.dendeberia.zooapi.service;

import com.dendeberia.zooapi.dao.domain.Animal;
import com.dendeberia.zooapi.dao.domain.Zone;
import com.dendeberia.zooapi.dao.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class AnimalService {

    private final Repositories repositories;
    private final ZoneService zoneService;

    @Autowired
    public AnimalService(WebApplicationContext webApplicationContext, ZoneService zoneService) {
        repositories = new Repositories(webApplicationContext);
        this.zoneService = zoneService;
    }

    private JpaRepository getRepository(Animal animal) {
        return (JpaRepository) repositories.getRepositoryFor(animal.getClass()).orElseThrow();
    }

    public Animal save(Animal animal){
        Zone zone = animal.getZone();

        if (zone == null){
            throw new IllegalArgumentException("Zone is required!");
        }

        Zone existedZone = zoneService.findById(zone.getId());
        animal.setZone(existedZone);

        AnimalRepository repository = (AnimalRepository) getRepository(animal);

        return repository.save(animal);
    }

    public List<Animal> findAll(Long zone, String name) {

        AnimalRepository animalRepository = (AnimalRepository) repositories.getRepositoryFor(Animal.class).orElseThrow();

        Stream<Animal> animalStream = animalRepository.findAll()
                .stream();

        if (zone != null){
            animalStream = animalStream.filter(animal -> animal.getZone().getId().equals(zone));
        }

        if (name != null){
            animalStream = animalStream.filter(animal -> animal.getName().equals(name));
        }

        return animalStream.collect(Collectors.toList());
    }
}
