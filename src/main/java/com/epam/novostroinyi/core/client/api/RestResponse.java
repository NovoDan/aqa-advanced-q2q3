package com.epam.novostroinyi.core.client.api;

import io.restassured.response.Response;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestResponse implements ApiResponse {

  private Response response;

  @Override
  public <T> T getResponse(Class<T> tClass) {
    return response.body().as(tClass);
  }

  @Override
  public int getStatusCode() {
    return response.statusCode();
  }

  @Override
  public String getResponseBody() {
    return response.body().prettyPrint();
  }
}
