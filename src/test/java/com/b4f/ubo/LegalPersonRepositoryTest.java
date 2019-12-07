package com.b4f.ubo;

import com.b4f.ubo.domain.BusinessEntity;
import com.b4f.ubo.domain.LegalPerson;
import com.b4f.ubo.domain.OwnerShip;
import com.b4f.ubo.helpers.BusinessEntityFactory;
import com.b4f.ubo.repositories.LegalPersonRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;



@Execution(ExecutionMode.CONCURRENT)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DataJpaTest
@Tag("db")
//provides some standard setup needed for testing the persistence layer: H2, Hibernate, Spring Data, and the DataSource, @EntityScan, turning on SQL logging
public class LegalPersonRepositoryTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LegalPersonRepository legalPersonRepository;

    @Test
    public void when_existing_id_the_corresponding_business_entity_should_be_returned() {


        // given
        BusinessEntity be01 = new BusinessEntity("Business Entity 01");
        BusinessEntity be02 = new BusinessEntity("Business Entity 02");

        List<OwnerShip> ownerships=List.of(
                new OwnerShip(50,be01),
                new OwnerShip(50,be02)
        );

        BusinessEntity sg = new BusinessEntity("SG",ownerships);

        entityManager.persist(be01);
        entityManager.persist(be02);


        sg=entityManager.persist(sg);
        entityManager.flush();

        // when
        Optional<LegalPerson> found = legalPersonRepository.findById(sg.getUUID());

        // then
        assertTrue(found.isPresent());

        LegalPerson foundLegalPerson = found.get();
        assertTrue(foundLegalPerson instanceof BusinessEntity);

        BusinessEntity foundBusinessEntity=(BusinessEntity)foundLegalPerson;
        assertEquals(foundBusinessEntity,sg);

    }

    @Test
    public void when_a_business_entity_is_persited_all_related_data_is_persisted() {

        //given
        BusinessEntity clientA = BusinessEntityFactory.createBusinessEntityHierarchy();

        BusinessEntity persistedBusinessEntity=legalPersonRepository.save(clientA);



        //when
        LegalPerson foundBusinessEntity = legalPersonRepository.findById(persistedBusinessEntity.getUUID()).get();

        //then
        assertEquals(clientA,persistedBusinessEntity);
        assertEquals(persistedBusinessEntity,foundBusinessEntity);

    }
}