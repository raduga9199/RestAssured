package com.cybertek.Day01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SampleTest {
    @Test
    public void calculatorTest(){
        assertEquals(2+2, 3+1, "Assertion Failed");
        assertEquals(10, 5+4,"Assertion Failed");

    }

    @DisplayName("I am testing the name")
    @Test
    public void nameTest(){
        String name = "Radu";
        assertEquals(name, "Radu", "Assertion FAILED");
        assertEquals(name, "Rad", "Assertion FAILED");
    }
}
