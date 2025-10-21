package com.springboot.ch07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LbsCh07Application {

	public static void main(String[] args) {
		SpringApplication.run(LbsCh07Application.class, args);
	}

}
