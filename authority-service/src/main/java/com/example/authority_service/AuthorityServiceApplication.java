package com.example.authority_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class AuthorityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorityServiceApplication.class, args);
	}

}
