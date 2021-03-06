package com.cybertek.Day02;

import com.cybertek.Day01.importsAndURL;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Practice1 extends importsAndURL {

    @DisplayName("Get All Characters Simple Test")
    @Test
    public void testBreakingBad(){
                 when().get(breakingBadURL())
                            .prettyPeek()
                .then().statusCode(is(200))
                            .header(contentType(),is("application/json; charset=utf-8"));


    }

}
