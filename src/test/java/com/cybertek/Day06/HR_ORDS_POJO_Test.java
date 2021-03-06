package com.cybertek.Day06;

import com.cybertek.Day01.importsAndURL;
import com.cybertek.pojo.Locations;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HR_ORDS_POJO_Test extends importsAndURL {
    @BeforeAll
    public static void init(){
        baseURI = baseSpartans_URI();
        port = 1000;
        basePath = "ords/hr";
    }

    @DisplayName("Testing the /location/{location_id} endpoint")
    @Test
    public void testLocation(){

        Response response =
                            given()
                                    .pathParam("location_id", 1700)
                                    .log().all().
                            when()
                                    .get("/locations/{location_id}")
                                    .prettyPeek();

        Locations l1 = response.as(Locations.class);




    }

}



















