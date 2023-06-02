package com.epam.novostroinyi.core.client.api;

import java.util.Map;

public interface ApiClient {

  public ApiResponse doGet(String url);

  public ApiResponse doGet(String url, Map params);

  public ApiResponse doPost(String url, Object body);

  public ApiResponse doPut(String url, Object body);

  public ApiResponse doDelete(String url, Object body);
}
