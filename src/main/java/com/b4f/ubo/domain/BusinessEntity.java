package com.b4f.ubo.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@NamedEntityGraph(
        name = "legalperson-graph",
        attributeNodes = {

                @NamedAttributeNode(value = "ownerships", subgraph = "ownerships-subgraph"),
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "ownerships-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("legalPerson")
                        }
                )
        }
)
@Entity
@DiscriminatorValue("BUSINESS_ENTITY")
public class BusinessEntity extends LegalPerson {

    @OneToMany( cascade = {CascadeType.PERSIST,CascadeType.DETACH})
    private List<OwnerShip> ownerships;

    public BusinessEntity() {
        super();
        this.ownerships=new ArrayList<>();
    }

    public BusinessEntity(String name) {
       this(name,new ArrayList<>());

    }


    public BusinessEntity(String name, List<OwnerShip> ownerships) {
        super(name);
        this.ownerships = ownerships;
    }


    public List<OwnerShip> getOwnerships() {
        return ownerships;
    }

    public void setOwnerships(List<OwnerShip> ownerships) {
        this.ownerships = ownerships;
    }

    public int size() {
        return ownerships.size();
    }

    public boolean add(OwnerShip ownerShip) {
        return ownerships.add(ownerShip);
    }



    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ownerships);
    }

    @Override
    public String toString() {
        return super.toString()+"{{{" +
                "ownerships=" + ownerships +
                "}}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusinessEntity)) return false;
        if (!super.equals(o)) return false;
        BusinessEntity that = (BusinessEntity) o;
        return Objects.equals(getOwnerships(), that.getOwnerships());
    }
}
