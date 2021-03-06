package com.cybertek.Day02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cybertek.Day01.importsAndURL;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class SpartanSearchTest_QueryParameter extends importsAndURL{
    @BeforeAll
    public static void setUp(){
        baseURI = baseSpartans_URI();
        basePath = baseSpartans_Path();
    }

    @DisplayName("Testing /spartans /search Endpoint")
    @Test
    public void testSearch(){
        given().
                log().all()
                .queryParam("gender", "male")
                .queryParam("nameContains", "a").
        when().
                get("spartans/search").

        then()
                .log().all()
                .statusCode(is(200));

    }

}
