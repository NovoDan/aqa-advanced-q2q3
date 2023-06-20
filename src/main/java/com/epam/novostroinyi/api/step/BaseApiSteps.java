package com.epam.novostroinyi.api.step;

import com.epam.novostroinyi.core.config.Property;

public class BaseApiSteps {

  private static final String API_ENDPOINT = "api/v1";
  private final String baseUrl = Property.COMMON_PROPERTY.baseUrl();
  private final String projectName = Property.COMMON_PROPERTY.rpProjectName();

  protected String createUrl(String endpoint) {
    return String.join("/", baseUrl, API_ENDPOINT, projectName, endpoint);
  }

  protected String createUrlMultipleEndpoints(String... endpoints) {
    return createUrl(String.join("/", endpoints));
  }

}
