package com.finclusion.ccppas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CcppasApplication {

	public static void main(String[] args) {
		System.out.println("test git issue");
		SpringApplication.run(CcppasApplication.class, args);
	}

}
