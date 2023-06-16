package com.epam.novostroinyi.core.client.api;

import java.util.Map;

public interface ApiClient {

  public ApiResponse doGet(String url);

  public ApiResponse doGet(String url, Map params);

  public <T> ApiResponse doPost(String url, T body);

  public <T> ApiResponse doPut(String url, T body);

  public <T> ApiResponse doDelete(String url, T body);
}
