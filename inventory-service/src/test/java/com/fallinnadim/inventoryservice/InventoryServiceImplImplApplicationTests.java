package com.fallinnadim.inventoryservice;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceImplImplApplicationTests {
	// Start postgresDBcontainer
	@ServiceConnection
	static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:14.11-alpine3.19");
	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		postgreSQLContainer.start();
	}

	@Test
	void shouldProductAvailable() {
		Boolean responseBody = RestAssured.given().contentType("application/json")
				.when()
				.get("api/inventories?sku_code=iPhone 15 Pro&quantity=50")
				.then()
				.statusCode(200)
				.extract()
				.body().as(Boolean.class);
		assertTrue(responseBody);
	}

}
