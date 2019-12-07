package com.b4f.ubo.dtos;


import com.b4f.ubo.domain.LegalPerson;

import java.util.Objects;
import java.util.UUID;



public class OwnerShipDTO {


    private UUID uuid;

    private double percentage;


    private LegalPersonDTO legalPerson;

    public OwnerShipDTO() {

    }

    public OwnerShipDTO(double percentage, LegalPersonDTO legalPerson) {
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

    public LegalPersonDTO getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(LegalPersonDTO legalPerson) {
        this.legalPerson = legalPerson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OwnerShipDTO)) return false;
        OwnerShipDTO that = (OwnerShipDTO) o;
        return Double.compare(that.getPercentage(), getPercentage()) == 0 &&
                Objects.equals(uuid, that.uuid) &&
                Objects.equals(getLegalPerson(), that.getLegalPerson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, getPercentage(), getLegalPerson());
    }

    @Override
    public String toString() {
        return "OwnerShipDTO{" +
                "uuid=" + uuid +
                ", percentage=" + percentage +
                ", legalPerson=" + legalPerson +
                '}';
    }
}
