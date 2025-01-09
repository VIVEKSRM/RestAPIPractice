package org.example;

import io.restassured.path.json.JsonPath;
import org.apache.http.io.SessionOutputBuffer;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
public class apiMainTest {


    @Test
    public void request()
    {
        baseURI="https://rahulshettyacademy.com/";
        String postResponce = given().header("Connection", "keep-alive")
                .formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type", "client_credentials")
                .formParam("scope", "trust")
                .when().log().all()
                .post("oauthapi/oauth2/resourceOwner/token")
                .then().assertThat().statusCode(200).extract().response().asString();

        JsonPath js = new JsonPath(postResponce);
        String accessToken=js.get("access_token");
        System.out.println(accessToken);

        String getResponse=given().queryParam("access_token",accessToken)
                .when().get("oauthapi/getCourseDetails")
                .then().log().all()
                .assertThat().statusCode(401)
                .extract().response().asString();
        System.out.println(getResponse);

    }

}
