package com.epam.novostroinyi.core.client.api;

import com.epam.novostroinyi.core.constant.StatusCode;
import com.epam.novostroinyi.core.exception.ApiRequestException;
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

  @Override
  public byte[] getFile() {
    return response.then().extract().asByteArray();
  }

  @Override
  public ApiResponse verifyStatusCode(StatusCode expectedCode) {
    if (response.statusCode() != expectedCode.getCode()) {
      throw new ApiRequestException("Status code does not match to the expected one\n"
          + "Expected %d but got %d".formatted(expectedCode.getCode(), response.statusCode()));
    }
    return this;
  }
}
