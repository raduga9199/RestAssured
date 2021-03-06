package com.cybertek.Day01;


import org.junit.jupiter.api.*;

public class BeforeAfterJUnit5 {

    @BeforeAll
    public static void setUp(){
        System.out.println("This runs before any test pr BeforeTest runs");
    }

    @BeforeEach
    public void beforeEachTest(){
        System.out.println("This Runs Before Each Test");
    }

    @AfterEach
    public void afterEachTest(){
        System.out.println("This Runs After Each Test");
    }

    @Test
    public void test1(){
        System.out.println("Test 1 is Running");
    }

    @Test
    public void test2(){
        System.out.println("Test 2 is Running");
    }

    @AfterAll
    public static void setup(){
        System.out.println("This runs all the way to the end");
    }

}

