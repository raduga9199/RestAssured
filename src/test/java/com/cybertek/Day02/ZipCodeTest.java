package com.cybertek.Day02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class ZipCodeTest {
    @BeforeAll
    public static void setUp(){
        baseURI = "http://api.zippopotam.us";
        basePath = "/us";
    }

    @DisplayName("Zip to City Test")
    @Test
    public void testZipToCity(){

        int zipCode = 32224;
        given()
                .pathParam("zipcode",zipCode)
                .log().all().
        when()
                .get("/{zipcode}").
        then()
                .log().all()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .body("'post code'", is(zipCode + ""))
                .body("country", is("United States"))
                .body("places[0].state", is("Florida"))
                .body("places[0].longitude", is("-81.4404"))
                .body("places[0].'place name'",is("Jacksonville"));

    }

    @DisplayName("City to Zip")
    @Test
    public void cityToZip(){
        given()
                .pathParam("state","FL")
                .pathParam("city", "Jacksonville")
                .log().all().
        when()
                .get("/{state}/{city}").
        then()
                .log().all()
                .statusCode(is(200))
                .body("'country abbreviation'", is("US"))
                .body("places[0].latitude", is("30.3375"))
                //if we want the last index of ... use index [ -1 ]
                .body("places[-1].latitude",is("30.3162"));


    }

}
