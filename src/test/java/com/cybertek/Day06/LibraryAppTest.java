package com.cybertek.Day06;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class LibraryAppTest {

    private static String libraryToken;

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://library1.cybertekschool.com";
        RestAssured.basePath = "/rest/v1";
            libraryToken = loginAndGetToken("librarian69@library", "KNPXrm3S");
    }

    @DisplayName("Send request tp /dashboard_stats")
    @Test
    public void testDashboardWithToken(){
                given()
                        .log().all()
                        .header("x-library-token",libraryToken).
                when()
                        .get("/dashboard_stats").
                then()
                        .log().all()
                        .statusCode(200);
    }

    public static String loginAndGetToken(String username, String password){
        String token = "";

        Response response =
                given()
                        .contentType(ContentType.URLENC)
                        .formParam("email", username)
                        .formParam("password", password).
                        when()
                        .post("/login");

        token = response.jsonPath().getString("token");

        return token;
    }

    @DisplayName("Testing POST /decode Endpoint")
    @Test
    public void decode(){

        given()
                .log().all()
                .accept(ContentType.JSON)
                .contentType(ContentType.URLENC)
                .formParam("token", libraryToken).
        when()
                .post("/decode").
        then()
                .log().all()
                .statusCode(is(200))
                .body("email",is("librarian69@Library"))
                .body("token",is(libraryToken));

    }

    @DisplayName("Send request to /dashboard_stats")
    @Test
    public void testDashBoardStatsWithToken(){

        given()
                .log().all()
                .header("x-library-token",libraryToken).
        when()
                .get("/dashboard_stats").
        then()
                .log().all()
                .statusCode(200)
                .body("book_count", is("1955"))
                .body("borrowed_books", is("681"))
                .body("users", is("5083"));

    }

    @DisplayName("Test /get_user_by_id/{id} Endpoint")
    @Test
    public void testSingleUserData(){
        given()
                .log().all()
                .header("x-library-token",libraryToken)
                .pathParam("id", 2080).
        when()
                .get("/get_user_by_id/{id}").
        then()
                .log().all()
                .statusCode(200)
                .body("id", is("2080"))
                .body("full_name", is("Test Student 142"))
                .body("image", is(nullValue()))
                .body("user_group_id", is("3"));






            }

}
