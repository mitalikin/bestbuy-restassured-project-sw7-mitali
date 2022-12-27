package com.bestbuy.crudtest;

import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoresCURDTest extends TestBase {
    @Test
    public void createNewStore() {
        StorePojo storePojo = new StorePojo();
        storePojo.setId(111);
        storePojo.setName("Prime");
        storePojo.setType("Testing");
        storePojo.setAddress("13 Hill Rd");
        storePojo.setAddress2("Surrey");
        storePojo.setCity("Bristol");
        storePojo.setState("Bl");
        storePojo.setZip("SW12 5TR");
        storePojo.setLat(44.879314);
        storePojo.setLng(-94.209656);
        storePojo.setHours("Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 9-9; Sat: 9-9; Sun: 10-8");

        Response response = given().log().all()
                .headers("Content-Type", "application/json")
                .when()
                .body(storePojo)
                .post();
        response.prettyPrint();
        response.then().log().all().statusCode(201);


    }


}
