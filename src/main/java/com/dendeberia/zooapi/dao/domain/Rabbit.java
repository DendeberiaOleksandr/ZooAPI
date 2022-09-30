package com.dendeberia.zooapi.dao.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Rabbit")
@DiscriminatorValue("Rabbit")
public class Rabbit extends Animal{

    public Rabbit(Long id, String name, Zone zone) {
        super(id, name, 4, zone);
    }

    public Rabbit() {
        super.setFeedNeeded(4);
    }
}
