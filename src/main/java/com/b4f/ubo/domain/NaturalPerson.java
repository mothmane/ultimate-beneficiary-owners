package com.b4f.ubo.domain;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NATURAL_PERSON")
public class NaturalPerson extends LegalPerson {


    public NaturalPerson() { }

    public NaturalPerson(String name) {
        super(name);
    }
}
