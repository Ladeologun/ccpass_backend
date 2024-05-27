package com.finclusion.ccppas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CcppasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CcppasApplication.class, args);
	}

}
