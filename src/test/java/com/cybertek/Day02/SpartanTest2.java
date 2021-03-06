package com.cybertek.Day02;

import com.cybertek.Day01.importsAndURL;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SpartanTest2 extends importsAndURL{

    @BeforeAll
    public static void setUp(){
        baseURI = baseSpartans_URI();
        basePath = baseSpartans_Path();
    }

    @DisplayName("Hello Test")
    @Test
    public void helloTest(){
        given()
                .accept(ContentType.TEXT).
        when()
                .get("/hello")
                .prettyPeek().
        then()
                .statusCode(is(200))
                .contentType(ContentType.TEXT)
                .body(equalTo("Hello from Sparta"));
    }

    @DisplayName("Get 1 Spartan")
    @Test
    public void getSingleSpartan(){



            // I want to log  the request I sent so I see what is the URL, methods and so on
            given()
                    .log().all().
                    //.log().uri().
            when()
                    .get("spartans/71").
                    //.prettyPeek().
            then()
                    .log().all()
                    //.log().body()
                    //.log().ifValidationFails()
                    .statusCode(is(200));
    }








    @DisplayName("Get All Spartans")
    @Test
    public void testAllSpartans() {
        baseURI = baseSpartans_URI();
        basePath = baseSpartans_Path();

        given().header("Accept","application/json").
        when().get("/spartans")
                .then().statusCode(is(200));

    }



    @DisplayName("Get All Spartans Test 2")
    @Test
    public void testAllSpartans2(){

        given().baseUri(baseSpartans_URI())
                .basePath(baseSpartans_Path())
        //      .accept("application/json").
                .accept(ContentType.JSON).
        when()
                .get("/spartans").
        then()
                .statusCode(is(200))
                .contentType(ContentType.JSON);


    }
}
