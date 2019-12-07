package com.b4f.ubo.controllers;


import com.b4f.ubo.domain.LegalPerson;
import com.b4f.ubo.domain.UltimateBeneficialOwnership;
import com.b4f.ubo.dtos.LegalPersonDTO;
import com.b4f.ubo.dtos.UltimateBeneficialOwnershipDTO;
import com.b4f.ubo.exceptions.LegalPersonNotFoundException;
import com.b4f.ubo.mappers.LegalPersonMapper;
import com.b4f.ubo.mappers.UBOMapper;
import com.b4f.ubo.services.LegalPersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(value="/v1/api/legal-persons", produces = "application/json")
public class LegalPersonController {

    private LegalPersonService legalPersonService;
    private UBOMapper uboMapper;
    private LegalPersonMapper legalPersonMapper;

    public LegalPersonController(LegalPersonService legalPersonService,
                                 UBOMapper uboMapper, LegalPersonMapper legalPersonMapper)
                                 {
        this.legalPersonService=legalPersonService;
        this.uboMapper=uboMapper;
        this.legalPersonMapper= legalPersonMapper;
    }

    @GetMapping()
    public List<LegalPersonDTO> all() {
        return legalPersonMapper.toDTOs(legalPersonService.findAll());
    }

    @GetMapping("/{id}")
    public LegalPersonDTO byId(@PathVariable UUID id) {
        return legalPersonMapper.toDTO(legalPersonService.findById(id).orElseThrow(() -> new LegalPersonNotFoundException(id)));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public LegalPersonDTO save(@RequestBody LegalPersonDTO legalPersonDTO) {
        return legalPersonMapper.toDTO(legalPersonService.save(legalPersonMapper.fromDTO(legalPersonDTO)));
    }
    @PutMapping()
    public LegalPersonDTO modify(@RequestBody LegalPersonDTO legalPersonDTO) {
        return legalPersonMapper.toDTO(legalPersonService.save(legalPersonMapper.fromDTO(legalPersonDTO)));
    }


    @GetMapping("/{id}/ubos")
    public List<UltimateBeneficialOwnershipDTO> ubos(@PathVariable UUID id) {
        return uboMapper.toDTOs(legalPersonService.computeUltimateBeneficialOwnershipById(id));
    }


}
