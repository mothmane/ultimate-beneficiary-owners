package com.b4f.ubo.services;

import com.b4f.ubo.domain.*;
import com.b4f.ubo.graph.UBODirectedWeightedGraph;
import com.b4f.ubo.repositories.LegalPersonRepository;

import org.jgrapht.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class LegalPersonService {

    private LegalPersonRepository legalPersonRepository;


    private UBODirectedWeightedGraph uboDirectedWeightedGraph;

    public LegalPersonService(LegalPersonRepository legalPersonRepository, UBODirectedWeightedGraph uboDirectedWeightedGraph) {
        this.legalPersonRepository = legalPersonRepository;
        this.uboDirectedWeightedGraph = uboDirectedWeightedGraph;

    }

    public Iterable<LegalPerson> findAll() {
        return legalPersonRepository.findAll();
    }

    public Optional<LegalPerson> findById(UUID id) {
        return legalPersonRepository.findById(id);
    }

    public LegalPerson save(LegalPerson legalPerson) {

        return legalPersonRepository.save(legalPerson);


    }

    public void deleteById(UUID id) {
        legalPersonRepository.deleteById(id);
    }

    public List<UltimateBeneficialOwnership> computeUltimateBeneficialOwnershipById(UUID id) {
        return this.legalPersonRepository.findById(id)
                .map(legalPerson -> computeUltimateBeneficialOwnership((BusinessEntity) legalPerson))
                .orElse(new ArrayList<>());

    }

    public List<UltimateBeneficialOwnership> computeUltimateBeneficialOwnership(BusinessEntity be) {
        return uboDirectedWeightedGraph.conputeAllUltimateBeneficialOwnership(be);


    }

    public List<NaturalPerson> filterByNaturalPerson(UUID id) {
        return this.legalPersonRepository.findById(id)
                .map(businessEntity -> this.filterByNaturalPerson((BusinessEntity) businessEntity)
                ).orElse(new ArrayList<>());
    }

    public List<NaturalPerson> filterByNaturalPerson(BusinessEntity businessEntity) {
        return uboDirectedWeightedGraph.filterByNaturalPerson(businessEntity);
    }

}
