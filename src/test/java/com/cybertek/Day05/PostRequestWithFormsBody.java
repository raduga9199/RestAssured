package com.cybertek.Day05;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostRequestWithFormsBody {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://library1.cybertekschool.com";
        RestAssured.basePath = "rest/v1";
    }



    @DisplayName("POST /login request test")
    @Test
    public void testLoginEndPoint(){

        given()
                .formParam("email","librarian69@library")
                .formParam("password", "KNPXrm3S").
        when().
                post("/login").
        then()
                .log().all()
                .statusCode(200)
                .body("token", is(notNullValue()));



    }


    @DisplayName("Testing the utility")
    @Test
    public void runningUtilityMethod(){
        System.out.println(loginAndGetToken("librarian69@library","KNPXrm3S"));
    }

    public static String loginAndGetToken(String username, String password){
        String token = "";

        Response response =
                given()
                        .formParam("email",username)
                        .formParam("password", password).
                when()
                        .post("/login");



            token = response.jsonPath().getString("token");


        return token;
    }


}
