package com.cybertek.Day03;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Map;

import static io.restassured.RestAssured.*;


public class ExtractingDataOutOfResponseBody {

    @DisplayName("Getting Movie Info")
    @Test
    public void test_1(){

        Response response =
                given()
                        .log().all()
                .queryParam("apikey", "26aa5b74")
                .queryParam("t", "Boss Baby").

        when()
                .get("http://www.omdbapi.com");

        //response.prettyPrint();
        System.out.println(response.statusCode());
//=================================================
        JsonPath jp = response.jsonPath();

        String title = jp.getString("Title");
        int year = jp.getInt("Year");

        System.out.println("title = " + title);
        System.out.println("year = " + year);

        String director = jp.getString("Director");

        System.out.println("director = " + director);



        String rating1Src = jp.getString("Ratings[0].Source");
        System.out.println("rating 1 Src = " + rating1Src);


        Map<String,Object> reposponseMap = jp.getMap("");

        System.out.println(reposponseMap);






    }

}
