package com.dendeberia.zooapi.dao.repository;

import com.dendeberia.zooapi.dao.domain.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findAllByZoneId(Long zoneId);

}
