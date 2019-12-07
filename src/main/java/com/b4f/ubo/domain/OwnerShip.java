package com.b4f.ubo.domain;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;


@Entity
public class OwnerShip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    private double percentage;

    @ManyToOne(cascade = CascadeType.ALL)
  //  @JoinColumn
    private LegalPerson legalPerson;

    public OwnerShip(){

    }

    public OwnerShip(double percentage, LegalPerson legalPerson) {
        this.percentage = percentage;
        this.legalPerson = legalPerson;
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public LegalPerson getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(LegalPerson legalPerson) {
        this.legalPerson = legalPerson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerShip ownerShip = (OwnerShip) o;
        return Double.compare(ownerShip.percentage, percentage) == 0 &&
                Objects.equals(legalPerson, ownerShip.legalPerson);
    }

    @Override
    public int hashCode() {
        return Objects.hash( percentage, legalPerson);
    }

    @Override
    public String toString() {
        return "OwnerShip{" +
                "uuid=" + uuid +
                ", percentage=" + percentage +
                ", legalPerson=" + legalPerson +
                '}';
    }
}
