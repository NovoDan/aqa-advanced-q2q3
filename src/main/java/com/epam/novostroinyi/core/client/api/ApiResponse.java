package com.epam.novostroinyi.core.client.api;

public interface ApiResponse {

  <T> T getResponse(Class<T> tClass);

  String getResponseBody();

  int getStatusCode();
}
