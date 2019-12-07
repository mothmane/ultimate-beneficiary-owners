package com.b4f.ubo;


import com.b4f.ubo.domain.BusinessEntity;
import com.b4f.ubo.domain.LegalPerson;
import com.b4f.ubo.domain.NaturalPerson;
import com.b4f.ubo.domain.UltimateBeneficialOwnership;
import com.b4f.ubo.graph.UBODirectedWeightedGraphImpl;
import com.b4f.ubo.helpers.BusinessEntityFactory;
import com.b4f.ubo.repositories.LegalPersonRepository;
import com.b4f.ubo.services.LegalPersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringJUnitConfig( {LegalPersonService.class, UBODirectedWeightedGraphImpl.class})
public class LegalPersonServiceTest {

    @Autowired
    private LegalPersonService legalPersonService;

    @MockBean
    private LegalPersonRepository legalPersonRepositoryMock;

    private BusinessEntity clientA;
    private UUID uuid;

    @BeforeEach
     void setUp() {

        this.clientA= BusinessEntityFactory.createBusinessEntityHierarchy();
        this.uuid= UUID.randomUUID();

        Mockito.when(legalPersonRepositoryMock.findById(uuid))
                .thenReturn(Optional.of(clientA));

    }

    @Test
     void when_not_existing_id_no_legal_person_should_be_returned() {
        //given
        var uuid = UUID.randomUUID();

        //when
        var found = legalPersonRepositoryMock.findById(uuid);

        //then
        assertThat(found.isEmpty()).isTrue();

    }

    @Test
     void when_valid_id_the_corresponding_legal_person_should_be_returned() {
        //given


        //when
        var found = legalPersonService.findById(this.uuid);

        //then
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getName()).isEqualTo(clientA.getName());
    }


    @Test
     void when_valid_Id_the_corresponding_list_UBOS_should_be_returned() {

        //when
       var found = legalPersonService.computeUltimateBeneficialOwnershipById(uuid);

        Supplier<Stream<UltimateBeneficialOwnership>> supplier = () -> StreamSupport.stream(found.spliterator(), false);


        //then
        String[] legalPersonNamesFound = supplier.get().
                map(UltimateBeneficialOwnership::getNaturalPerson).
                map(NaturalPerson::getName).toArray(size -> new String[size]);
        String[] legalPersonNamesExpected = {"John", "Mary"};

        Double[] legalPersonUBOsFound = supplier.get()
                    .map(UltimateBeneficialOwnership::getPercentage)
                    .toArray(size -> new Double[size]);
        Double[] legalPersonUBOSExpected = {0.449, 0.21600000000000003};

        assertThat(supplier.get().count()).isEqualTo(2);
        assertThat(legalPersonNamesFound).isEqualTo(legalPersonNamesExpected );
        assertThat(legalPersonUBOsFound).isEqualTo(legalPersonUBOSExpected);

    }
}
