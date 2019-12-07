package com.b4f.ubo.config;

import com.b4f.ubo.domain.BusinessEntity;
import com.b4f.ubo.domain.LegalPerson;
import com.b4f.ubo.domain.NaturalPerson;
import com.b4f.ubo.dtos.BusinessEntityDTO;
import com.b4f.ubo.dtos.LegalPersonDTO;
import com.b4f.ubo.dtos.NaturalPersonDTO;
import com.b4f.ubo.mappers.LegalPersonMapper;
import com.b4f.ubo.mappers.OwnerShipMapper;
import com.b4f.ubo.mappers.UBOMapper;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappersConfiguration {


    @Bean
    public ModelMapper modelMapper(){
        var modelMapper= new ModelMapper();

        TypeMap<LegalPerson, LegalPersonDTO> typeMap=modelMapper.createTypeMap(LegalPerson.class, LegalPersonDTO.class);
        typeMap.include(NaturalPerson.class, NaturalPersonDTO.class)
                .include(BusinessEntity.class, BusinessEntityDTO.class);
        return modelMapper;
    }

    @Bean
    public LegalPersonMapper legalPersonMapper(ModelMapper modelMapper,OwnerShipMapper ownerShipMapper){
        LegalPersonMapper lp= new LegalPersonMapper(modelMapper);
        lp.setOwnerShipMapper(ownerShipMapper);
        ownerShipMapper.setLegalPersonMapper(lp);
        return lp;
    }

    @Bean
    public OwnerShipMapper ownerShipMapper(ModelMapper modelMapper){
        return new OwnerShipMapper(modelMapper);
    }


    @Bean
    public UBOMapper uboMapper(ModelMapper modelMapper){
        return new UBOMapper(modelMapper);
    }

}
