package com.b4f.ubo.dtos;


import com.b4f.ubo.domain.LegalPerson;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class BusinessEntityDTO extends LegalPersonDTO {

    @JsonProperty("ownerships")
    private List<OwnerShipDTO> ownerships;

    public BusinessEntityDTO() {
        this.ownerships= new ArrayList<>();
    }

    public BusinessEntityDTO(String name) {
        super(name);
        this.ownerships= new ArrayList<>();
    }
    public BusinessEntityDTO(UUID uuid , String name) {
        this(uuid,name,new ArrayList<OwnerShipDTO>());
    }

    public BusinessEntityDTO(UUID uuid , String name, List<OwnerShipDTO> ownerships) {
        super(uuid,name);
        this.ownerships = ownerships;
    }


    public List<OwnerShipDTO> getOwnerships() {
        return ownerships;
    }

    public void setOwnerships(List<OwnerShipDTO> ownerships) {
        this.ownerships = ownerships;
    }

    public int size() {
        return ownerships.size();
    }

    public boolean add(OwnerShipDTO ownerShip) {
        return ownerships.add(ownerShip);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusinessEntityDTO)) return false;
        BusinessEntityDTO that = (BusinessEntityDTO) o;
        return Objects.equals(getOwnerships(), that.getOwnerships());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOwnerships());
    }

    @Override
    public String toString() {
        return super.toString()+"{" +
                "ownerships=" + ownerships +
                '}';
    }
}
