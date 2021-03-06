package com.cybertek.Day01;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Practice1 {

    @Test
    public void test1() {
        Response response = get("http://54.205.44.112:8000/api/hello");


        System.out.println("Response code is: " + response.statusCode());
        System.out.println("Status line is: " + response.statusLine());

        System.out.println("Response of the header " + response.getHeader("Date"));

        System.out.println("Response of the Header Content-Type " + response.getHeader("content-type"));
    }

    @DisplayName("Testing /hello endpoint")
    @Test
    public void testHello() {
        Response response = get("http://54.205.44.112:8000/api/hello");
        assertEquals(200, response.getStatusCode(), "Status Code FAILED");
        assertEquals("text/plain;charset=UTF-8", response.getHeaders(), "GetHeader test FAILED");
        assertEquals("text/plain;charset=UTF-8", response.contentType(), "content type test FAILED");
        assertEquals("17", response.header("Content-length"), "Content length test FAILED");
    }

    @DisplayName("Testing /hello endpoint body")
    @Test
    public void testingHelloResponseBody() {
        Response response = get("http://54.205.44.112:8000/api/hello");

        System.out.println(response.asString());
        System.out.println(response.body().asString());


        // Positive Testing
        assertEquals(17, response.body().asString().length()
                , "Length of body text is not matching with expected body length");

        // Negative Testing
        //assertEquals(18, response.body().asString().length()
        //        ,"Length of body text is not matching with expected body length");

        // Positive Testing
        assertEquals("Hello from Sparta", response.body().asString()
                , "Actual body message doesn't match with expected body message");

        // Negative Testing --> if assertion fails then it will stop --> to continue we need Soft Assertion
        assertEquals("Hello from Sparta!", response.body().asString()
                , "Actual body message doesn't match with expected body message");

    }

    @DisplayName("Printing the response body using method")
    @Test
    public void printingBody() {
        Response response = get("http://54.205.44.112:8000/api/hello");

        String prettyPrint = response.prettyPrint();
        System.out.println(prettyPrint);

        response.prettyPeek();

        int statusCode = response.prettyPeek().statusCode();
        System.out.println("PRINTING ONLY STATUS CODE " + statusCode);

    }


}