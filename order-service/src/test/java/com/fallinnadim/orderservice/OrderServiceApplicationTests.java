package com.fallinnadim.orderservice;

import com.fallinnadim.orderservice.stubs.InventoryClientStub;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.testcontainers.containers.PostgreSQLContainer;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class OrderServiceApplicationTests {

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
    void shouldCreateOrder() {
        String requestBody = """
                {
                	"sku_code":"iPhone 15 Pro",
                	"price":1400,
                	"quantity":5
                }
                """;
        String sku_code = "iPhone 15 Pro";
        InventoryClientStub.stubInventoryCall(URLEncoder.encode(sku_code, StandardCharsets.UTF_8).replace("+", "%20"), 5);
        String responseBodyString = RestAssured.given().contentType("application/json")
                .body(requestBody)
                .when()
                .post("api/orders")
                .then()
                .statusCode(201)
                .extract()
                .body().asString();
        assertThat(responseBodyString, Matchers.is("Order Placed Successfully"));
    }
}
