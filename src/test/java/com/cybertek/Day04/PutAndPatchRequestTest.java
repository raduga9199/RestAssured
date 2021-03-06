package com.cybertek.Day04;
import com.cybertek.pojo.Spartan;
import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cybertek.Day01.importsAndURL;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;



public class PutAndPatchRequestTest extends importsAndURL{
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = baseSpartans_URI();
        RestAssured.port = spartanPort_8000();
        RestAssured.basePath = baseSpartans_Path();
    }

    @DisplayName("Put Request body as a Map")
    @Test
    public void testPutRequestWithMap(){

        String randomName = new Faker().name().firstName();




        Map<String,Object> updateBody = new LinkedHashMap<>();
        updateBody.put("name",randomName);
        updateBody.put("gender","Male");
        updateBody.put("phone",1234567899L);

        // Test without POJO
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(updateBody).
                when()
                .put("/spartans/{id}",112).
                then()
                .log().all()
                .statusCode(is(204));


        Spartan spartan = new Spartan(randomName, "Female", 9876543210L);

        //Test with POJO
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan).
        when()
                .put("/spartans/{id}",112).
        then()
                .log().all()
                .statusCode(is(204));




        when()
                .get("/spartans/{id}",112).
        then()
                .log().all()
                .statusCode(is(200))
                .body("name", is(randomName));


    }

    @DisplayName("Patch Request")
    @Test
    public void testPathPartialUpdate(){

        String randomName = new Faker().name().firstName();
    //  String patchBody = "\"name\" : \" " + randomName + "\"";

        Map<String,Object> pathBodyMap = new HashMap<>();

        pathBodyMap.put("name", randomName);

        // PATCH REQUEST --> partial update
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(pathBodyMap).
        when()
                .patch("/spartans/{id}",111).
        then()
                .log().all()
                .statusCode(204);

    }

    @DisplayName("Post a new Spartan and match the id")
    @Test
    public void postSpartanAndTestID(){

    }
}
