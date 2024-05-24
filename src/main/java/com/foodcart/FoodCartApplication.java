package com.foodcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoodCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodCartApplication.class, args);
		System.out.println("App is running");
	}

}
