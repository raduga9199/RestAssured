package com.cybertek.Day06;

import com.cybertek.Day01.importsAndURL;
import com.cybertek.pojo.Locations;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

import java.util.List;

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

        System.out.println("Country ID is: " + l1.getCountry_id());
        System.out.println("Location ID is: " + l1.getLocationID());
        System.out.println("City is: " + l1.getCity());
        System.out.println("Postal code is: " + l1.getPostal_code());
        System.out.println("State Province is: " + l1.getState_province());
        System.out.println("Street Address is: " + l1.getStreet_address());


    }

    @DisplayName("Testing the /location endpoint")
    @Test
    public void testLocations(){
        Response response =
                                get("/locations");


        List<Locations> locations =
                                        response.jsonPath().getList("items",Locations.class);
        locations.forEach(each -> System.out.println("Version 1: " + each));

        for (Locations each : locations){
            System.out.println("Version 2: " + each);
        }



    }


}



















