package com.cybertek.Day04;

import com.cybertek.Day01.importsAndURL;
import com.cybertek.pojo.Spartan;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class PostRequestsWithPJO extends importsAndURL {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = baseSpartans_URI();
        RestAssured.port = spartanPort_8000();
        RestAssured.basePath = baseSpartans_Path();
    }

    @Test
    public void testPostBodyWithPojo(){
        Spartan spartan_1 = new Spartan("Alex", "Male", 9876543210L);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan_1).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201);

    }
}
