package com.example.crudBasics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
public class CrudBasicsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudBasicsApplication.class, args);
		System.out.println("Application Started");
	}

}
