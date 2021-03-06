package com.cybertek.Day01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class RestAssuredMethodChaining extends importsAndURL{

    @DisplayName("First Attempt for chaining")
    @Test
    public void chainingMethodsTest1(){
        //given().
        //when().
        //then().

        when().get(url_api_hello()).then().statusCode(is(200)).header("content-length", "17");
        when().get(url_api_hello()).then().statusCode(200).header("content-length", "17");

    }

    @DisplayName("Using Hamcrest matcher for asserion")
    @Test
    public void testingWithMatcher(){
        when().get(url_api_hello()).prettyPeek()
                .then().statusCode(is(200)).header("content-length", "17")
                .header("content-type", "text/plain;charset=UTF-8")
                .body(is("Hello from Sparta"));

    }

    @DisplayName("Testing GET /api/Spartans end point")
    @Test
    public void testAllSpartans(){

        // JSON format
        when().get(url_api_spartans()).prettyPeek()
                .then().statusCode(is(200));

        // XML Format
            given().header("Accept","application/xml").
        when().get(url_api_spartans()).prettyPeek()
                .then().statusCode(is(200));

    }
}
