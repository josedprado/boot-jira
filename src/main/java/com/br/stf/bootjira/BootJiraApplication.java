package com.br.stf.bootjira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.br.stf.resource")
public class BootJiraApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootJiraApplication.class, args);
	}
}
