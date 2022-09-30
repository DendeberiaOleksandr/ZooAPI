package com.dendeberia.zooapi.dao.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Elephant")
@DiscriminatorValue("Elephant")
public class Elephant extends Animal{

    public Elephant(Long id, String name, Zone zone) {
        super(id, name, 20, zone);
    }

    public Elephant() {
        super.setFeedNeeded(20);
    }
}
