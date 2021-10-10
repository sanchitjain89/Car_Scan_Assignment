package com.carScan.assignment;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Car Scan API's", version = "2.0", description = "Car Scan CRUD User API's"))
public class CarScanAssignmentProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarScanAssignmentProjectApplication.class, args);
	}
}
