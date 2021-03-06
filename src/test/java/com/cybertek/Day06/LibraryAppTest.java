package com.cybertek.Day06;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class LibraryAppTest {

    private static String libraryToken;

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://library1.cybertekschool.com";
        RestAssured.basePath = "/rest/v1";
            libraryToken = loginAndGetToken("librarian69@Library", "KNPXrm3S");
    }

    @DisplayName("Send request tp /dashboard_stats")
    @Test
    public void testDashboardWithToken(){
                given()
                        .log().all()
                        .header("x-library-token",libraryToken).
                when()
                        .get("/dashboard_stats").
                then()
                        .log().all()
                        .statusCode(200);
    }

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

    @DisplayName("Testing POST /decode Endpoint")
    @Test
    public void decode(){

        given()
                .log().all()
                .accept(ContentType.JSON)
                .contentType(ContentType.URLENC)
                .formParam("token", libraryToken).
        when()
                .post("/decode").
        then()
                .log().all()
                .statusCode(is(200))
                .body("email",is("librarian69@Library"))
                .body("token",is(libraryToken));








    }



}
