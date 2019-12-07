package com.b4f.ubo.mappers;

import com.b4f.ubo.domain.UltimateBeneficialOwnership;
import com.b4f.ubo.dtos.UltimateBeneficialOwnershipDTO;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;


public class UBOMapper implements GenericMapper<UltimateBeneficialOwnership, UltimateBeneficialOwnershipDTO> {

    private ModelMapper mapper;


    public UBOMapper(ModelMapper mapper){
        this.mapper=mapper;
    }

    @Override
    public UltimateBeneficialOwnership fromDTO(UltimateBeneficialOwnershipDTO dto) {
        return mapper.map(dto,UltimateBeneficialOwnership.class);
    }

    @Override
    public UltimateBeneficialOwnershipDTO toDTO(UltimateBeneficialOwnership domain) {
        return mapper.map(domain,UltimateBeneficialOwnershipDTO.class);
    }
}
