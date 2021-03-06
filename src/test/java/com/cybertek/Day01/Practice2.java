package com.cybertek.Day01;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class Practice2 {

    @DisplayName("assertThat Test 1")
    @Test
    public void Test(){
        int num1 = 5;
        int num2 = 4;



        assertThat(num1 + num2, is(9));
        assertThat(num1 + num2, equalTo(9));

        assertThat(num1 + num2, not(8));
        assertThat(num1 + num2, is( not(8)));

        String firstName = "Radu";
        String lastName = "Gusanu";

        String fullName = firstName + " " + lastName;


        assertThat(firstName + " " + lastName,equalTo("Radu Gusanu"));
        assertThat(fullName,equalToIgnoringCase("Radu Gusanu"));

        assertThat((firstName + lastName).toLowerCase(), equalToCompressingWhiteSpace("radugusanu"));


    }

    @DisplayName("Support for Collection")
    @Test
    public void test2(){
        List<Integer> numList = Arrays.asList(11,44,3,55,88,5);

        assertThat(numList.size(), equalTo(6));
        assertThat(numList, hasSize(6));
        assertThat(numList,hasItem(11));
        assertThat(numList, containsInAnyOrder(44,11,3,55,88,5));
        assertThat(numList, hasItems(44,11,3,55));
    }
}
