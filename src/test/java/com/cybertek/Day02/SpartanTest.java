package com.cybertek.Day02;

import com.cybertek.Day01.importsAndURL;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanTest extends importsAndURL{

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
