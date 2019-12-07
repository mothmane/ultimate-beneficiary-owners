package com.b4f.ubo;

import com.b4f.ubo.config.MappersConfiguration;
import com.b4f.ubo.domain.BusinessEntity;
import com.b4f.ubo.domain.NaturalPerson;
import com.b4f.ubo.dtos.BusinessEntityDTO;
import com.b4f.ubo.dtos.LegalPersonDTO;
import com.b4f.ubo.helpers.BusinessEntityFactory;
import com.b4f.ubo.mappers.LegalPersonMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@Execution(ExecutionMode.CONCURRENT)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MappersConfiguration.class)
public class LegalPersonMapperTest {




    @Autowired
    private LegalPersonMapper legalPersonMapper;

    @Test
    public void should_Map_NaturalPerson_To_NaturalPersojnDTO() {
        //given
       NaturalPerson p = new NaturalPerson( "john" );
       p.setUUID(UUID.randomUUID());

        //when
        LegalPersonDTO npDTO=  (legalPersonMapper.toDTO(p));
        //then
        Assertions.assertThat( npDTO ).isNotNull();
        Assertions.assertThat( npDTO.getName() ).isEqualTo( "john" );

    }

    @Test
    public void should_Map_ClientA_To_clientADTO() {
        //given
        BusinessEntity domain = BusinessEntityFactory.createBusinessEntityHierarchy();
        BusinessEntityDTO expectedDTO = BusinessEntityFactory.createBusinessEntityDTOHierarchy();


        //when
        BusinessEntityDTO resultedDTO=  (BusinessEntityDTO)legalPersonMapper.toDTO(domain);

        //then
        // TODO more assertions
        Assertions.assertThat( resultedDTO ).isNotNull();
        Assertions.assertThat( expectedDTO.getOwnerships().get(0).getLegalPerson().getName()).isEqualTo(resultedDTO.getOwnerships().get(0).getLegalPerson().getName());



    }


}
