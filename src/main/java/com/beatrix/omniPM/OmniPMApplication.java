package com.beatrix.omniPM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OmniPMApplication
{
	public static void main(String[] args) {
		SpringApplication.run(OmniPMApplication.class, args);
	}

}
