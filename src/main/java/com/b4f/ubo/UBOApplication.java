package com.b4f.ubo;

import com.b4f.ubo.domain.BusinessEntity;
import com.b4f.ubo.repositories.LegalPersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UBOApplication {

	public static void main(String[] args) {
		SpringApplication.run(UBOApplication.class, args);
	}



}
