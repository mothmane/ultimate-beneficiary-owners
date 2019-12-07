package com.b4f.ubo.dtos;

import com.b4f.ubo.domain.NaturalPerson;

import java.util.Objects;

public class UltimateBeneficialOwnershipDTO {

    private double percentage;

    private NaturalPersonDTO naturalPerson;

    public UltimateBeneficialOwnershipDTO() {
    }

    public UltimateBeneficialOwnershipDTO(double percentage, NaturalPersonDTO naturalPerson) {
        this.percentage = percentage;
        this.naturalPerson = naturalPerson;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public NaturalPersonDTO getNaturalPerson() {
        return naturalPerson;
    }

    public void setNaturalPerson(NaturalPersonDTO naturalPerson) {
        this.naturalPerson = naturalPerson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UltimateBeneficialOwnershipDTO)) return false;
        UltimateBeneficialOwnershipDTO that = (UltimateBeneficialOwnershipDTO) o;
        return Double.compare(that.getPercentage(), getPercentage()) == 0 &&
                Objects.equals(getNaturalPerson(), that.getNaturalPerson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPercentage(), getNaturalPerson());
    }
}
