package com.b4f.ubo.dtos;



import com.b4f.ubo.domain.LegalPerson;

import java.util.UUID;


public class NaturalPersonDTO extends LegalPersonDTO {

    public NaturalPersonDTO() { }

    public NaturalPersonDTO(String name) {
        super(name);
    }

    public NaturalPersonDTO(UUID uuid,String name) {
        super(uuid,name);
    }

}
