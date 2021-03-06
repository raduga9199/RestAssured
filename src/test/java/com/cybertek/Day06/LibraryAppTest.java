package com.cybertek.Day06;

import org.junit.jupiter.api.BeforeAll;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class LibraryAppTest {

    private static String libraryToken;

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://library1.cybertekschool.com";
        RestAssured.basePath = "/rest/v1";

    }

    @DisplayName("Send request tp /dashboard_stats")
    @Test
    public void testDashboardWithToken(){
                given()
                        .log().all()
                        .header("x-library-token","").
                when()
                        .get("/dashboard_stats").
                then()
                        .log().all()
                        .statusCode(200);
    }
}
