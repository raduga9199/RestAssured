package com.cybertek.Day02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cybertek.Day01.importsAndURL;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

    public class SpartanTest_Parameters extends importsAndURL{
        @BeforeAll
        public static void setUp(){
            baseURI = baseSpartans_URI();
            basePath = baseSpartans_Path();
        }

        @DisplayName("Testing /spartans/{id}")
        @Test
        public void testSingleSpartan() {
        given()
                .log().all()
                .pathParam("id",80).
        when()
                .get("spartans/{id}").
        then()
                .statusCode(is(200));

    }

        @DisplayName("Testing 2 /spartans/{id}")
        @Test
        public void testSingleSpartan2() {
            given()
                    .log().all().
            when()
                    .get("spartans/{id}",71).
            then()
                    .statusCode(is(200));
        }

        @DisplayName("Testing /spartans/{id} Body")
        @Test
        public void testSingleSpartanBody(){

            given()
                    .log().all()
                    .pathParam("id", 10).
            when()
                    .get("spartans/{id}",10).
            then()
                    .log().all()
                    .statusCode(is(200))
                    .body("id", is(10))
                    .body("name",is("Lorenza"))
                    .body("gender",is("Female"))
                    .body("phone",is(3312820936L));
        }
}
