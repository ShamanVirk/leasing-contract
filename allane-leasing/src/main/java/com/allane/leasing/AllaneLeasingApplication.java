package com.allane.leasing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.allane.leasing")
public class AllaneLeasingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllaneLeasingApplication.class, args);
	}
}
