package com.cybertek.Day05;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LibraryStarter {

    public static String loginAndGetToken(String username, String password){
        String token = "";

        Response response =
                                given()
                                        .contentType(ContentType.URLENC)
                                            .formParam("email", username)
                                            .formParam("password", password).
                                when()
                                        .post("/login");

        token = response.jsonPath().getString("token");

        return token;
    }

}
