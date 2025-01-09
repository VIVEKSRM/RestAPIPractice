package org.example;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import serializationPojo.Location;
import serializationPojo.Request;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class serializationUsingPojo {
    @Test
    public void serializationTest()
    {
        Request request=new Request();
        request.setAccuracy(50);
        request.setName("Frontline house");
        request.setPhone_number("(+91) 983 893 3937");
        request.setAddress("29, side layout, cohen 09");
        request.setWebsite("http://google.com");
        request.setLanguage("French-IN");

        ArrayList<String> types= new ArrayList<>();
        types.add("shoe park");
        types.add("shop");

        request.setTypes(types); //added ArrayList values

        Location location=new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);

        request.setLocation(location); //added location object values

        RestAssured.baseURI="https://rahulshettyacademy.com";
        String responce=given().queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body(request)
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .log().all().extract().response().asString();
        System.out.println(responce);
    }
}
