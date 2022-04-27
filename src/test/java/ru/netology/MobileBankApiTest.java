package ru.netology;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;



class MobileBankApiTest {
    @Test
    void shouldReturnTwoHundred() {
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("http://localhost:9999/api/v1")
                // Выполняемые действия
                .when()
                .get("/demo/accounts")
                // Проверки
                .then()
                .statusCode(200);
    }

    @Test
    void shouldReturnJson() {
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("http://localhost:9999/api/v1")
                // Выполняемые действия
                .when()
                .get("/demo/accounts")
                // Проверки
                .then()
                .contentType(ContentType.JSON);
    }

    @Test
    void shouldSatisfySchema() {

        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .body(matchesJsonSchemaInClasspath("accounts.schema.json"))
        ;
    }
    @Test
    void shouldHaveNotMinusAccounts() {

        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .body("[0].balance", greaterThanOrEqualTo(0))
                .body("[1].balance", greaterThanOrEqualTo(0))
                .body("[2].balance", greaterThanOrEqualTo(0))
        ;
    }
}