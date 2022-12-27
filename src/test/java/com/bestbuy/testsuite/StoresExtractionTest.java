package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import jdk.nashorn.internal.objects.annotations.Where;
import org.junit.BeforeClass;
import org.junit.Test;
import sun.plugin2.os.windows.Windows;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    //1. Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th store
    @Test
    public void test003() {
        String nameOfFifthStore = response.extract().path("data[5].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name Of Fifth Store is : " + nameOfFifthStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all the store
    @Test
    public void test004() {
        List<String> nameOfAllStore = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name Of All Stores are : " + nameOfAllStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the storeId of all the store
    @Test
    public void test005() {
        List<Integer> idOfAllStore = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The id Of All Stores are : " + idOfAllStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the size of the data list
    @Test
    public void test006() {
        List<Integer> nameOfAllStore = response.extract().path("data");
        //List<String> nameOfAllStore = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size Of All data list is : " + nameOfAllStore.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {
        // List<?> allStoreValue = response.extract().path("data.findAll{it.name=='St Cloud.'}.value");
        List<HashMap<String, ?>> allStoreValue = response.extract().path("data.findAll{it.name=='St Cloud'}.value");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size Of All data list is : " + allStoreValue);
        System.out.println("------------------End of Test---------------------------");

    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void test008() {

        List<HashMap<String, ?>> address = response.extract().path("data.findAll{it.name=='Rochester'}.address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The adress Of Address of Rochester is : " + address);
        System.out.println("------------------End of Test---------------------------");

    }

    //9. Get all the services of 8th store
    @Test
    public void test009() {
        List<String> services = response.extract().path("data[8].services");
        //List<HashMap<String,?>> services= response.extract().path("data.findAll{it.id==8}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all the services of 8th store are : " + services);
        System.out.println("The all the services of 8th store are : " + services.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test010() {
        //  List<String> storeServiceWindows = response.extract().path("data.findAll{it.name=='Windows Store'}.storeservices");
        List<?> storeServiceWindows = response.extract().path("data.find{it.services}.services.findAll{it.name=='Windows Store'}.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The services of store where service name = Windows Store : " + storeServiceWindows);
        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get all the storeId of all the store
    @Test
    public void test011() {
        List<?> storeId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("StoreID of All Store : " + storeId);
        System.out.println("------------------End of Test---------------------------");
    }

    //12. Get id of all the store
    @Test
    public void test012() {
        List<?> id = response.extract().path("data.services.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Id of All Store  : " + id);
        System.out.println("------------------End of Test---------------------------");
    }

    //13. Find the store names Where state = ND
    @Test
    public void test013() {
        List<?> nameND = response.extract().path("data.findAll{it.state=='ND'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("store names Where state = ND  : " + nameND);
        System.out.println("------------------End of Test---------------------------");
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014() {
        List<?> servicesOfRochester = response.extract().path("data.findAll{it.name=='Rochester'}.services.find{it.id}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total number of services where store name = Rochester  : " + servicesOfRochester.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test15() {//---still need to do
        //List<HashMap<String, ?>> createdAtWindowsStore= response.extract().path("data.find{it.services}.services.findAll{it.name=='Windows Store'}.storeservices.createdAt");
        List<?> createdAtWindowsStore = response.extract().path("data.findAll{it.name=='Windows Store'}.services.find{it.createdAt}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("store createdAt whose name = “Windows Store”  : " + createdAtWindowsStore);
        System.out.println("------------------End of Test---------------------------");
    }

    // 16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test16() {
        List<HashMap<String, ?>> servicesOfFargo = response.extract().path("data.findAll{it.name=='Fargo'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("store names Where tore name = “Fargo”  : " + servicesOfFargo);
        System.out.println("------------------End of Test---------------------------");
    }

    // 17. Find the zip of all the store
    @Test
    public void test017() {
        List<?> zip = response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("store Zip   : " + zip);
        System.out.println("------------------End of Test---------------------------");
    }

    //18. Find the zip of store name = Roseville
    @Test
    public void test018() {
        List<?> zipOfRoseville = response.extract().path("data.findAll{it.name=='Roseville'}.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of store name = Roseville: " + zipOfRoseville);
        System.out.println("------------------End of Test---------------------------");
    }

    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019() {
        List<?> storeservices = response.extract().path("data.findAll{it.name=='Magnolia Home Theater'}.services");
        //List<?>storeservices=response.extract().path("data.find{it.services}.services.findAll{it.name=='Magnolia Home Theater'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeservices of service name = Magnolia Home Theater: " + storeservices);
        System.out.println("------------------End of Test---------------------------");
    }

    //20. Find the lat of all the stores
    @Test
    public void test020() {
        List<?> lat = response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the lat of all the stores: " + lat);
        System.out.println("------------------End of Test---------------------------");
    }

}
