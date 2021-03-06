package com.cybertek.Day05;

import com.cybertek.pojo.Region;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;

import com.cybertek.Day01.importsAndURL;
import com.cybertek.pojo.Spartan;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class RegionTests extends importsAndURL{

    @BeforeAll
    public static void setUp(){
        baseURI = baseSpartans_URI();
        port = 1000;
        basePath = "ords/hr";


    }

    @DisplayName("GET region ID")
    @Test
    public void regionIDTest(){

        Response response =
                            given()
                                    .accept(ContentType.JSON)
                                    .pathParam("region_id",1).
                            when()
                                    .get("/regions/{region_id}")
                                    .prettyPeek();


        Region region = response.as(Region.class);
        System.out.println("region = " + region);

    }


    @DisplayName("Testing the /regions endpoint")
    @Test
    public void testRegionJsonArrayToPOJOList(){

        Response response = get("/regions");


        JsonPath jsonPath = response.jsonPath();

        List<Region> regionList = jsonPath.getList("items",Region.class);

        System.out.println(jsonPath.getString("items[0].region_name"));
        regionList.forEach(each -> System.out.println("Each : " + each));


    }




}
