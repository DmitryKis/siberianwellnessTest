package ru.dmitry.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestUtils {

    private static RequestSpecification specs;
    private static RestAssuredConfig config;
    public static Response httpPost(String json, String url) {
        return given()
                .spec(getSpecs())
                .headers(getDefaultHeaders())
                .body(json)
                .post(url);
    }

    public static Response httpGet(String url) {
        return given()
                .spec(getSpecs())
                .headers(getDefaultHeaders())
                .get(url);
    }

    public static RequestSpecification getSpecs() {
        if (specs == null) {
            specs = new RequestSpecBuilder()
                    .setConfig(config)
                    .setRelaxedHTTPSValidation()
                    .build();
        }
        return specs;
    }

    public static Headers getDefaultHeaders() {
        return new Headers(new Header("Content-type", "application/json"),
                new Header("Connection", "keep-alive"),
                new Header("token", "fd49d3cd-789c-4d00-912d-3382ca4f3968"));
    }
}
