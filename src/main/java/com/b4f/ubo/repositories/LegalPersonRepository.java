package com.b4f.ubo.repositories;

import com.b4f.ubo.domain.LegalPerson;
import com.b4f.ubo.domain.OwnerShip;
import net.bytebuddy.description.modifier.Ownership;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author maniar othmane
 */
public interface LegalPersonRepository extends CrudRepository<LegalPerson, UUID>, SpecialSaveRepository{
    @EntityGraph(value = "legalperson-graph")
    Optional<LegalPerson> findOneWithOwnershipsByUuid(UUID id);

    @EntityGraph(value = "ownerships.legalPerson")
    Optional<OwnerShip> findOneOwnershipsByUuid(UUID id);

}
