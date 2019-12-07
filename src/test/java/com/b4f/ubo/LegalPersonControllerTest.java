package com.b4f.ubo;

import com.b4f.ubo.config.MappersConfiguration;
import com.b4f.ubo.controllers.LegalPersonController;
import com.b4f.ubo.domain.BusinessEntity;
import com.b4f.ubo.domain.LegalPerson;
import com.b4f.ubo.exceptions.LegalPersonNotFoundException;
import com.b4f.ubo.helpers.BusinessEntityFactory;
import com.b4f.ubo.services.LegalPersonService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@WebMvcTest(LegalPersonController.class)
@Import({MappersConfiguration.class})
public class LegalPersonControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LegalPersonService service;

    @Test
    public void when_the_legal_persons_api_byid_url_is_called_should_return_a_json_legalperson()
            throws Exception {

        var uuid = UUID.randomUUID();
        var john = new LegalPerson("John");
        john.setUUID(uuid);

        given(service.findById(ArgumentMatchers.any())).willReturn(Optional.of(john));

        mvc.perform(get("/v1/api/legal-persons/"+uuid)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type", is("legal-person")))
                .andExpect(jsonPath("$.uuid", is(john.getUUID().toString())))
                .andExpect(jsonPath("$.name", is(john.getName())));

    }

    @Test
    public void when_the_legal_persons_api_byid_url_is_called_should_return_a_json_businessentity()
            throws Exception {

        var uuid = UUID.randomUUID();
        var be = new BusinessEntity("Corporate");
        be.setUUID(uuid);

        given(service.findById(ArgumentMatchers.any())).willReturn(Optional.of(be));

        mvc.perform(get("/v1/api/legal-persons/"+uuid)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type", is("business-entity")))
                .andExpect(jsonPath("$.uuid", is(be.getUUID().toString())))
                .andExpect(jsonPath("$.name", is(be.getName())));

    }

    @Test
    public void when_the_legal_persons_api_byid_url_is_called_should_return_a_ubos_list()
            throws Exception {

       var uuid=UUID.randomUUID();
        var john= BusinessEntityFactory.createUltimateBeneficialOwnerships(0.449,"John");
        var mary= BusinessEntityFactory.createUltimateBeneficialOwnerships(0.21600000000000003,"Mary");


        given(service.computeUltimateBeneficialOwnershipById(ArgumentMatchers.any())).willReturn(List.of(john,mary));

        //given(service.findById(ArgumentMatchers.any())).willReturn(Optional.of(BusinessEntityFactory.createBusinessEntityHierarchy()));

        mvc.perform(get("/v1/api/legal-persons/"+uuid+"/ubos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].percentage",is(0.449)));
    }


    @Test
    public void when_the_legal_persons_api_byid_url_is_called_with_non_existing_id_should_return_an_http_NOTFOUND_404()
            throws Exception {
        var uuid = UUID.randomUUID();

        given(service.findById(uuid)).willThrow(new LegalPersonNotFoundException(uuid));

        mvc.perform(get("/v1/api/legal-persons/" + uuid)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
/*
    @Test
    public void when_the_legal_persons_api_links_url_is_called_it_should_return_a_json__list_of_links()
            throws Exception {

        mvc.perform(get("/v1/api/legal-persons/links")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

*/
}
