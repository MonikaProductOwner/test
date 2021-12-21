package com.cg.ofda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //triggers auto-configuration and component scanning
public class SprintFoodDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprintFoodDeliveryApplication.class, args);
		System.out.println("Spring is Started");
	}

}
