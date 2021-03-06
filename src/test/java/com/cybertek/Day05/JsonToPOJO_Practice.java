package com.cybertek.Day05;


import com.cybertek.pojo.Spartan_2;
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

public class JsonToPOJO_Practice extends importsAndURL{
    @BeforeAll
    public static void init(){
        baseURI = baseSpartans_URI();
        port = spartanPort_8000();
        basePath = baseSpartans_Path();
    }

    @DisplayName("Json to POJO GET /spartans/{id}")
    @Test
    public void testSpartanJsonToSpartanObject(){


        Response response =
                            given()
                                    .auth().basic("admin", "admin")
                                    .log().all()
                                    .pathParam("id", 115).
                            when()
                                    .get("/spartans/{id}")
                                    .prettyPeek();


        Spartan_2 spartan_2 = response.as(Spartan_2.class);

        System.out.println("Spartan_2 = " + spartan_2);

    }

    @DisplayName("Search Request and save 1st Result as Spartan_2 POJO")
    @Test
    public void gettingNestedJsonAsPOJO(){
        Response response =
                            given()
                                    .log().all()
                                    .auth().basic("admin", "admin")
                                    .queryParam("gender","Male").
                            when()
                                    .get("/spartans/search")
                                    .prettyPeek();

        System.out.println("response.statusCode() = " + response.statusCode());

        JsonPath jp = response.jsonPath();

        System.out.println("First ID " + jp.getInt("content[0].id"));
        System.out.println("First Name " + jp.getString("content[0].name"));

        Spartan_2 firstMaleSpartan = jp.getObject("content[0]", Spartan_2.class);
        System.out.println("First male Spartan is: " + firstMaleSpartan);

        System.out.println("The Spartan id from POJO is: " +firstMaleSpartan.getId());
        System.out.println("The Spartan name from POJO is: " +firstMaleSpartan.getName());
        System.out.println("The Spartan gender from POJO is: " +firstMaleSpartan.getGender());
        System.out.println("The Spartan phone from POJO is: " +firstMaleSpartan.getPhone());
    }

    @DisplayName("Save json array as List<Spartan_2>")
    @Test
    public void testJsonArrayToListOfPOJO(){
        Response response =
                                given()
                                        .auth().basic("admin", "admin")
                                        .queryParam("gender", "Female").
                                when()
                                        .get("/spartans/search");

        JsonPath jsonPath = response.jsonPath();

        List<Integer> ids = jsonPath.getList("content.id");
        System.out.println(ids);

        List<String> names = jsonPath.getList("content.name");
        System.out.println(names);

        List<String> gender = jsonPath.getList("content.gender");
        System.out.println(gender);

        List<Long> phones = jsonPath.getList("content.phone");
        System.out.println(phones);


        List<Spartan_2> spartan_2List = jsonPath.getList("content",Spartan_2.class);
        System.out.println("Spartan 2 List = " + spartan_2List);

        for (Spartan_2 each : spartan_2List){
            System.out.println(each);
        }

        spartan_2List.forEach(each -> System.out.println(each));


    }

}
