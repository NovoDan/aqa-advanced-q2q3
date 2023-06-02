package com.epam.novostroinyi.core.client.api;

import com.epam.novostroinyi.core.constant.StatusCode;

public interface ApiResponse {

  <T> T getResponse(Class<T> tClass);

  String getResponseBody();

  int getStatusCode();

  byte[] getFile();

  ApiResponse verifyStatusCode(StatusCode expectedCode);
}
