package org.example;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class deserializationUsingPojo {


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

        // here we are getting Strings hence directly from Json path we can extract
        System.out.println("****postResponce :- "+postResponce);
        JsonPath js = new JsonPath(postResponce);
        String accessToken=js.get("access_token");
        System.out.println(accessToken);

        //With Pojo --------------IMP CONCEPT-------------
/*        GetResponce getResponse=given().queryParam("access_token",accessToken)
                .when().log().all()
                .get("oauthapi/getCourseDetails")
                .then().log().all()
                .assertThat().statusCode(401)
                .extract().response()
                .as(GetResponce.class);
        System.out.println("Instructor :- "+getResponse.getInstructor());
        System.out.println("getCourses :- "+getResponse.getCourses().getWebAutomation());
        System.out.println("getWebAutomation :- "+getResponse.getCourses().getWebAutomation().get(0).getCourseTitle());
        System.out.println("getLinkedIn :- "+getResponse.getLinkedIn());*/

        //From JsonPath Directly -----------------IMP CONCEPT ----------
               String getStringResponse=given().queryParam("access_token",accessToken)
                .when().log().all()
                .get("oauthapi/getCourseDetails")
                .then().log().all()
                .assertThat().statusCode(401)
                .extract().response()
                .asString();

        System.out.println("getStringResponse :- "+getStringResponse);
        JsonPath js1 = new JsonPath(getStringResponse);

        System.out.println(js1.getString("instructor"));
        System.out.println(js1.getString("courses.webAutomation[0].courseTitle"));

    }

}
