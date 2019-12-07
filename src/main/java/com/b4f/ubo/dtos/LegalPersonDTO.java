package com.b4f.ubo.dtos;


import com.b4f.ubo.domain.BusinessEntity;
import com.b4f.ubo.domain.NaturalPerson;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @Type(value = LegalPersonDTO.class, name = "legal-person"),
        @Type(value = NaturalPersonDTO.class, name = "natural-person"),
        @Type(value = BusinessEntityDTO.class, name = "business-entity")
})
public class LegalPersonDTO {


    private UUID uuid;
    private String name;

    public LegalPersonDTO() {
    }

    public LegalPersonDTO( String name) {
        this.name = name;
    }

    public LegalPersonDTO(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LegalPersonDTO)) return false;
        LegalPersonDTO that = (LegalPersonDTO) o;
        return Objects.equals(uuid, that.uuid) &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, getName());
    }

    @Override
    public String toString() {
        return "LegalPersonDTO{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                '}';
    }
}
