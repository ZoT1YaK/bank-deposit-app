package com.bank.controller;

import com.bank.model.DepositRequest;
import com.bank.repository.DepositRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class DepositResourceTest {

    @Inject
    DepositRepository repository;

    @BeforeEach
    public void resetDatabase() {
        cleanDb();
    }

    @Transactional
    public void cleanDb() {
        repository.deleteAll();
    }

    @Test
    public void testGetAllDepositsInitiallyEmpty() {
        given()
                .when().get("/deposits")
                .then()
                .statusCode(200)
                .body("$", hasSize(0)); //
    }

    @Test
    public void testCreateValidDepositRequest() {
        DepositRequest request = new DepositRequest();
        request.setCustomerId("CUST001");
        request.setCustomerName("Alice");
        request.setDepositAmount(1500);
        request.setCurrency("EUR");
        request.setTerm("1 year");

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/deposits")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("customerName", equalTo("Alice"));
    }

    @Test
    public void testCreateInvalidDepositRequest_shouldFail() {
        DepositRequest request = new DepositRequest();
        request.setCustomerId("CUST002");
        request.setCustomerName("Bob");
        request.setDepositAmount(500);
        request.setCurrency("USD");
        request.setTerm("1 month");

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/deposits")
                .then()
                .statusCode(400);
    }
}