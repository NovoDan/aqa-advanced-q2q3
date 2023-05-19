package com.epam.novostroinyi.api.step;

import static org.assertj.core.api.Assumptions.assumeThat;

import com.epam.novostroinyi.core.client.api.ApiClient;
import com.epam.novostroinyi.core.client.api.ApiResponse;
import com.epam.novostroinyi.core.config.ConfigUtils;
import com.epam.novostroinyi.core.constant.StatusCode;

public class LaunchesApiSteps extends BaseApiSteps {

  private static final String LAUNCH_BASE_ENDPOINT = "launch";
  private final ApiClient client = ConfigUtils.getClient();

  public ApiResponse getLaunchesList() {
    ApiResponse response = client.doGet(createUrl(LAUNCH_BASE_ENDPOINT));
    assumeThat(response.getStatusCode()).isEqualTo(StatusCode.OK.getCode());
    return response;
  }


}
