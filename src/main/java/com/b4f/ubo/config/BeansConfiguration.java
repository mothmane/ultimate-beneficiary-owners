package com.b4f.ubo.config;

import com.b4f.ubo.graph.UBODirectedWeightedGraph;
import com.b4f.ubo.graph.UBODirectedWeightedGraphImpl;
import com.b4f.ubo.repositories.LegalPersonRepository;

import com.b4f.ubo.services.LegalPersonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public LegalPersonService legalPersonService(LegalPersonRepository legalPersonRepository, UBODirectedWeightedGraph uboDirectedWeightedGraph){
        return new LegalPersonService(legalPersonRepository,uboDirectedWeightedGraph);
    }

    @Bean
    public UBODirectedWeightedGraph uboDirectedWeightedGraph(){
        return new UBODirectedWeightedGraphImpl();
    }


}
