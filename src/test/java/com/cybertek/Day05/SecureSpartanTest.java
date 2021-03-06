package com.cybertek.Day05;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;

import com.cybertek.Day01.importsAndURL;
import com.cybertek.pojo.Spartan;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;


public class SecureSpartanTest extends importsAndURL{

    @BeforeAll
    public static void init(){
        baseURI = baseSpartans_URI();
        port = spartanPort_8000();
        basePath = baseSpartans_Path();
    }

    @DisplayName("Test GET /spartan/{id} End point with Basic Auth")
    @Test
    public void testGetSingleSpartanSecured(){
        given()
                .log().all()
                .pathParam("id", 112)
        .when()
                .get("/spartans/{id}").
        then()
                .log().all()
                .statusCode(200);
    }


    @DisplayName("Test GET /spartan/{id} End point with Basic Auth")
    @Test
    public void testGettingSpartanWithCredentials(){
        int newId = createRandomSpartan();

        given()
                .log().all()
                .auth().basic("admin", "admin")
                .pathParam("id", newId).
        when()
                .get("/spartans/{id}").
        then()
                .log().all()
                .statusCode(200);

    }

    public static int createRandomSpartan(){
        Faker faker = new Faker();
        String name = faker.name().firstName();
        String gender = faker.demographic().sex();
        long phone = faker.number().numberBetween(1000000000L, 9999999999L);

        Spartan spartan = new Spartan(name, gender, phone);

        Response response = given()
                                    .log().ifValidationFails()
                                    .auth().basic("admin", "admin")
                                    .contentType(ContentType.JSON)
                                    .body(spartan).
                            when()
                                    .post("/spartans");

        return response.jsonPath().getInt("data.id");


    }

}
