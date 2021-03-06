package com.cybertek.Day04;


import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cybertek.Day01.importsAndURL;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;


public class PostRequestTest extends importsAndURL{

    @BeforeAll
    public static void init(){
        baseURI = baseSpartans_URI();
        port = spartanPort_8000();
        basePath = baseSpartans_Path();
    }

    @DisplayName("Post request with String as body")
    @Test
    public void testPostWithStringBody(){

        Faker faker = new Faker();
        String randomName = faker.name().firstName();

        System.out.println("Random firstName : " + randomName);



        String bodyString = "{\n" +
                " \"name\" : \"" + randomName + "\",\n" +
                " \"gender\" : \"Male\",\n" +
                " \"phone\" : 1230001122 \n" +
                "}";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyString).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(is(201))
                .body("data.name", is(randomName))
                ;
    }

    @DisplayName("Posting With external json file")
    @Test
    public void testPostWithExternalFile(){
        File file_1 = new File("spartan.json");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(file_1).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .body("data.name", is("Glinda"));
    }

    @DisplayName("Posting with Map object as body")
    @Test
    public void testPostWithMap(){
        Map<String,Object> bodyMap = new HashMap<>();
        bodyMap.put("name", "Vincent");
        bodyMap.put("gender", "Male");
        bodyMap.put("phone", 1233210123L);

        System.out.println("bodyMap = " + bodyMap);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyMap).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201);

    }

}
