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

public class importsAndURL {
    public static String breakingBadURL(){
        return "https://www.breakingbadapi.com/api/characters";
    }

    public static String url_api_hello(){
        return "http://54.205.44.112:8000/api/hello";
    }

    public static String baseSpartans_Path(){
        return "/api";
    }

    public static String baseSpartans_URI(){
        return "http://54.205.44.112";
    }

    public static int spartanPort_8000(){
        return 8000;
    }

    public static String url_api_spartans(){
        return "http://54.205.44.112:8000/api/spartans";
    }

    public static String helloFromSpartaMessage(){
        return "Hello from Sparta";
    }

    public static String contentTypeResult(){
        return "text/plain;charset=UTF-8";
    }

    public static String contentType(){
        return "content-type";
    }


    public static String contentLengthResult(){
        return "17";
    }

    public static String contentLength(){
        return "content-length";
    }
}
