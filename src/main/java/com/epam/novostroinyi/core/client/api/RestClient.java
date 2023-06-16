package com.epam.novostroinyi.core.client.api;

import static io.restassured.RestAssured.given;

import com.epam.novostroinyi.core.config.Property;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

public class RestClient implements ApiClient {

  private final RequestSpecification requestSpecification = new RequestSpecBuilder()
      .addHeaders(Map.of(
          "Authorization", "Bearer " + Property.SECRET_PROPERTY.reportPortalToken(),
          "Content-Type", "application/json",
          "Accept", "application/json"))
      .build();

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
  public <T> ApiResponse doPost(String url, T body) {
    return new RestResponse(given()
        .spec(requestSpecification)
        .body(body)
        .when()
        .post(url));
  }

  @Override
  public <T> ApiResponse doPut(String url, T body) {
    return new RestResponse(given()
        .spec(requestSpecification)
        .body(body)
        .when()
        .put(url));
  }

  @Override
  public <T> ApiResponse doDelete(String url, T body) {
    return new RestResponse(given()
        .spec(requestSpecification)
        .body(body)
        .when()
        .delete(url));
  }
}
