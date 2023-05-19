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
  public ApiResponse doPost(String url, Object body) {
    return (ApiResponse) given()
        .spec(requestSpecification)
        .body(body)
        .when()
        .post(url);
  }

  @Override
  public ApiResponse doPut(String url, Object body) {
    return (ApiResponse) given()
        .spec(requestSpecification)
        .body(body)
        .when()
        .put(url);
  }

  @Override
  public ApiResponse doDelete(String url, Object body) {
    return (ApiResponse) given()
        .spec(requestSpecification)
        .body(body)
        .when()
        .delete(url);
  }
}
