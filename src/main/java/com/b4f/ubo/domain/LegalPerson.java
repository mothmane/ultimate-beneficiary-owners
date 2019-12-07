package com.b4f.ubo.domain;


import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;


@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE")
@DiscriminatorValue("LEGAL_PERSON")
public class LegalPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    private String name;

    public LegalPerson() { }

    public LegalPerson( String name) {
        this.name=name;
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
        if (!(o instanceof LegalPerson)) return false;
        LegalPerson that = (LegalPerson) o;
        return Objects.equals(uuid, that.uuid) &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, getName());
    }

    @Override
    public String toString() {
        return "LegalPerson{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                '}';
    }
}
