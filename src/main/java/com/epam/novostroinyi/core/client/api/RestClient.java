package com.epam.novostroinyi.core.client.api;

import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

public class RestClient implements ApiClient {

  private final RequestSpecification requestSpecification;

  public RestClient(Map<String, String> headers) {
    requestSpecification = new RequestSpecBuilder()
        .addHeaders(headers)
        .build();
  }

  @Override
  public ApiResponse doGet(String url) {
    return new RestResponse(given()
        .spec(requestSpecification)
        .when()
        .get(url));
  }

  @Override
  public ApiResponse doGet(String url, Map params) {
    return new RestResponse(given()
        .queryParams(params)
        .spec(requestSpecification)
        .when()
        .get(url));
  }

  @Override
  public ApiResponse doPost(String url, Object body) {
    return new RestResponse(given()
        .spec(requestSpecification)
        .body(body)
        .when()
        .post(url));
  }

  @Override
  public ApiResponse doPut(String url, Object body) {
    return new RestResponse(given()
        .spec(requestSpecification)
        .body(body)
        .when()
        .put(url));
  }

  @Override
  public ApiResponse doDelete(String url, Object body) {
    return new RestResponse(given()
        .spec(requestSpecification)
        .body(body)
        .when()
        .delete(url));
  }
}
