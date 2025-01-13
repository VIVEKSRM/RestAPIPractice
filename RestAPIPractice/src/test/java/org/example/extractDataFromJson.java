package org.example;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

public class extractDataFromJson {
    @Test
    public void jsontest() {
        String jsonObjectString = "{\n" +
                "    \"instructor\": \"RahulShetty\",\n" +
                "    \"url\": \"rahulshettycademy.com\",\n" +
                "    \"services\": \"projectSupport\",\n" +
                "    \"expertise\": \"Automation\",\n" +
                "    \"courses\": {\n" +
                "        \"webAutomation\": [\n" +
                "            {\n" +
                "                \"courseTitle\": \"Selenium Webdriver Java\",\n" +
                "                \"price\": \"50\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"courseTitle\": \"Cypress\",\n" +
                "                \"price\": \"40\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"courseTitle\": \"Protractor\",\n" +
                "                \"price\": \"40\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"api\": [\n" +
                "            {\n" +
                "                \"courseTitle\": \"Rest Assured Automation using Java\",\n" +
                "                \"price\": \"50\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"courseTitle\": \"SoapUI Webservices testing\",\n" +
                "                \"price\": \"40\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"mobile\": [\n" +
                "            {\n" +
                "                \"courseTitle\": \"Appium-Mobile Automation using Java\",\n" +
                "                \"price\": \"50\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"linkedIn\": \"https://www.linkedin.com/in/rahul-shetty-trainer/\"\n" +
                "}";

      //  System.out.println(jsonObjectString);
        JsonPath js = new JsonPath(jsonObjectString);
        System.out.println(js.getString("instructor"));
        System.out.println(js.getString("courses.webAutomation[0].courseTitle"));
        System.out.println(js.getInt("courses.api[1].price"));
    }
}
