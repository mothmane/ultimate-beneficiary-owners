package com.b4f.ubo.domain;

public class UltimateBeneficialOwnership{

    private double percentage;

    private NaturalPerson naturalPerson;

    public UltimateBeneficialOwnership() {
    }

    public UltimateBeneficialOwnership(double percentage, NaturalPerson naturalPerson) {
        this.percentage = percentage;
        this.naturalPerson = naturalPerson;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public NaturalPerson getNaturalPerson() {
        return naturalPerson;
    }

    public void setNaturalPerson(NaturalPerson naturalPerson) {
        this.naturalPerson = naturalPerson;
    }

    @Override
    public String toString() {
        return "UltimateBeneficialOwnership{" +
                "percentage=" + percentage +
                ", naturalPerson=" + naturalPerson +
                '}';
    }
}
