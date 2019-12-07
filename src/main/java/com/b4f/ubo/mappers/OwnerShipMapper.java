package com.b4f.ubo.mappers;

import com.b4f.ubo.domain.LegalPerson;
import com.b4f.ubo.domain.OwnerShip;
import com.b4f.ubo.domain.UltimateBeneficialOwnership;
import com.b4f.ubo.dtos.OwnerShipDTO;
import com.b4f.ubo.dtos.UltimateBeneficialOwnershipDTO;
import org.modelmapper.ModelMapper;

public class OwnerShipMapper implements GenericMapper<OwnerShip, OwnerShipDTO> {

    private ModelMapper mapper;
    private LegalPersonMapper legalPersonMapper;

    public OwnerShipMapper(ModelMapper mapper){
        this.mapper=mapper;
    }

    public void setLegalPersonMapper(LegalPersonMapper legalPersonMapper) {
        this.legalPersonMapper = legalPersonMapper;
    }

    @Override
    public OwnerShip fromDTO(OwnerShipDTO dto) {
        var domain= mapper.map(dto, OwnerShip.class);
       domain.setLegalPerson(legalPersonMapper.fromDTO(dto.getLegalPerson()));
        return domain;
    }

    @Override
    public OwnerShipDTO toDTO(OwnerShip domain) {
        var dto= mapper.map(domain,OwnerShipDTO.class);
       dto.setLegalPerson(legalPersonMapper.toDTO(domain.getLegalPerson()));
        return dto;
    }
}