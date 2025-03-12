package com.credenceid.issuance.service;

import com.credenceid.issuance.service.domain.coordinator.IssuanceCoordinator;
import com.credenceid.issuance.service.domain.coordinator.IssuanceCoordinatorBuilderImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IssuanceServiceApplication {

	private IssuanceCoordinator issuanceCoordinator = new IssuanceCoordinatorBuilderImpl().build();

	public static void main(String[] args) {
		SpringApplication.run(IssuanceServiceApplication.class, args);
	}

}
