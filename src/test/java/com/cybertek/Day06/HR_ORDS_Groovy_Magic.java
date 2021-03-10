package com.cybertek.Day06;

import com.cybertek.Day01.importsAndURL;
import com.cybertek.pojo.Locations;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.stream.Location;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class HR_ORDS_Groovy_Magic  extends importsAndURL {
    @BeforeAll
    public static void init(){
        baseURI = baseSpartans_URI();
        port = 1000;
        basePath = "ords/hr";
    }

    @DisplayName("Testing the /Employees endpoint")
    @Test
    public void testEmployees(){
        Response response = get("/employees");//.prettyPeek();

        JsonPath jsonPath =  response.jsonPath();

        System.out.println("All ID's " + jsonPath.getList("items.employee_id"));
        System.out.println("Last id: " +jsonPath.getInt("items[0].employee_id"));
        System.out.println("Last id: " +jsonPath.getInt("items[-1].employee_id"));


        System.out.println
                ("fFrom the first till the fifth: " + jsonPath.getList("items[0..4].employee_id"));

        System.out.println("Last names from id's 10 to 15th employee: " +
        jsonPath.getList("items[10..15].last_name"));


        String result = jsonPath.getString("items.find{ it.employee_id == 105}.last_name");
        System.out.println("result Last name = " + result);

        int salary = jsonPath.getInt("items.find{it.email=='LDEHAAN'}.salary");

        System.out.println("salary = " + salary);

        List<String> richPeople = jsonPath.getList("items.findAll{it.salary>15000}first_name");
        System.out.println("richPeople first_name = " + richPeople);


        List<String> phonesDep90 = jsonPath.getList("items.findAll{it.department_id == 90 }.phone_number");
        System.out.println("phonesDep90 = " + phonesDep90);

        List<Locations> locationsList =
                response.jsonPath().getList("items",Locations.class);

        assertThat(locationsList,hasSize(25));

        List<Locations> usLocations =
                response.jsonPath().getList("items.findAll{it.country_id=='US'}",Locations.class);

        usLocations.forEach(each -> System.out.println(each));
        
        
        int maxSalary = jsonPath.getInt("items.max{it.salary}.salary");
        System.out.println("maxSalary is: " + maxSalary);




    }
}
