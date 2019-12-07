package com.b4f.ubo.mappers;

import com.b4f.ubo.domain.BusinessEntity;
import com.b4f.ubo.domain.LegalPerson;
import com.b4f.ubo.domain.NaturalPerson;
import com.b4f.ubo.dtos.BusinessEntityDTO;
import com.b4f.ubo.dtos.LegalPersonDTO;
import com.b4f.ubo.dtos.NaturalPersonDTO;
import org.modelmapper.ModelMapper;

import java.util.UUID;


public class LegalPersonMapper implements GenericMapper<LegalPerson, LegalPersonDTO> {

    private ModelMapper mapper;
    private OwnerShipMapper ownerShipMapper;

    public LegalPersonMapper(ModelMapper mapper){
        this.mapper=mapper;

    }

    public void setOwnerShipMapper(OwnerShipMapper ownerShipMapper) {
        this.ownerShipMapper = ownerShipMapper;
    }

    @Override
    public LegalPerson fromDTO(LegalPersonDTO dto) {

        if(dto instanceof NaturalPersonDTO) {
            return mapper.map( dto,NaturalPerson.class);
        }
        if(dto instanceof BusinessEntityDTO) {
            var be= mapper.map( dto,BusinessEntity.class);
            be.setOwnerships(ownerShipMapper.fromDTOs(((BusinessEntityDTO) dto).getOwnerships()));
            return be;
        }
        return mapper.map( dto,LegalPerson.class);

    }

    @Override
    public LegalPersonDTO toDTO(LegalPerson domain) {
        if (domain==null) return null;
        if(domain instanceof NaturalPerson) {
            return mapper.map( domain,NaturalPersonDTO.class);
        }
        if(domain instanceof BusinessEntity) {
            var be= mapper.map( domain,BusinessEntityDTO.class);
            be.setOwnerships(ownerShipMapper.toDTOs(((BusinessEntity) domain).getOwnerships()));
            return be;
        }
        return mapper.map(domain,LegalPersonDTO.class);
    }
}
