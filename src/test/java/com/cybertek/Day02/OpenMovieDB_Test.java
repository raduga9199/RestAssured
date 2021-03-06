package com.cybertek.Day02;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cybertek.Day01.importsAndURL;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class OpenMovieDB_Test {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://www.omdbapi.com";
    }

    @DisplayName("Open Movie DB_Test")
    @Test
    public void openMovieDB_Test_1(){

        given()
                .log().all()
                .queryParam("apikey", "26aa5b74")
                .queryParam("t", "Boss Baby")
                .queryParam("plot", "full").

        when()
                .get().

        then()
                .log().all()
                .statusCode(is(200))
                .body("Title", containsString("Boss Baby"))
                .body("Ratings[].Value", is("6.3/10"));



    }
}
