package com.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiFinancialApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiFinancialApplication.class, args);
	}

}
