package com.spring.multi.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class MultipleDbDriversApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultipleDbDriversApplication.class, args);
	}

}
