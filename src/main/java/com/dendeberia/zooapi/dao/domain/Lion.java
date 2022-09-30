package com.dendeberia.zooapi.dao.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Lion")
@DiscriminatorValue("Lion")
public class Lion extends Animal{

    public Lion(Long id, String name, Zone zone) {
        super(id, name, 11, zone);
    }

    public Lion() {
        super.setFeedNeeded(11);
    }
}
