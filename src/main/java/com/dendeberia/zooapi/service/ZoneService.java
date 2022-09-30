package com.dendeberia.zooapi.service;

import com.dendeberia.zooapi.dao.domain.Animal;
import com.dendeberia.zooapi.dao.domain.Zone;
import com.dendeberia.zooapi.dao.repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ZoneService {

    private final ZoneRepository zoneRepository;

    public Zone save(Zone zone) {
        return zoneRepository.save(zone);
    }

    public Zone findById(Long id) {
        return zoneRepository.findById(id).orElseThrow();
    }

    public Zone findMoreFeedNeeded() {
        List<Zone> zones = zoneRepository.findAll();

        if (!zones.isEmpty()){
            Zone moreFeedNeededZone = zones.get(0);
            int max = 0;

            for (Zone zone: zones){
                int feedNeeded = 0;

                List<Animal> animals = zone.getAnimals();

                for (Animal animal: animals){
                    feedNeeded += animal.getFeedNeeded();
                }

                if (feedNeeded > max){
                    moreFeedNeededZone = zone;
                    max = feedNeeded;
                }

            }

            return moreFeedNeededZone;

        }
        throw new IllegalStateException("Zone list is empty!");
    }

    public Zone findMinimalAnimalsZone() {
        List<Zone> zones = zoneRepository.findAll();
        Zone minimalAnimalsZone;
        if (!zones.isEmpty()){
            minimalAnimalsZone = zones.get(0);
            int minimal = Integer.MAX_VALUE;

            for (Zone zone: zones){
                int animalsSize = zone.getAnimals().size();

                if (animalsSize < minimal){
                    minimal = animalsSize;
                    minimalAnimalsZone = zone;
                }

            }

            return minimalAnimalsZone;

        }
        throw new IllegalStateException("Zone list is empty!");
    }
}
