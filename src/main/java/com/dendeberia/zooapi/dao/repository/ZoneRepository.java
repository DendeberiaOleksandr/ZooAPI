package com.dendeberia.zooapi.dao.repository;

import com.dendeberia.zooapi.dao.domain.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {
}
