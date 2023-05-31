package com.epam.novostroinyi.api.step;

import static com.epam.novostroinyi.core.util.FileUtils.getFileAsStringByPath;
import static com.epam.novostroinyi.core.util.JsonUtils.convertToJson;
import static com.epam.novostroinyi.core.util.JsonUtils.readJsonSingleValue;
import static org.assertj.core.api.Assertions.assertThat;

import com.epam.novostroinyi.core.client.api.ApiClient;
import com.epam.novostroinyi.core.client.api.ApiResponse;
import com.epam.novostroinyi.core.config.ConfigUtils;
import com.epam.novostroinyi.core.constant.StatusCode;
import java.util.List;
import java.util.Map;

public class LaunchesApiSteps extends BaseApiSteps {

  private static final String LAUNCH_BASE_ENDPOINT = "launch";
  private final ApiClient client = ConfigUtils.getClient();

  public ApiResponse getLaunchesList() {
    ApiResponse response = client.doGet(createUrl(LAUNCH_BASE_ENDPOINT));
    assertThat(response.getStatusCode()).isEqualTo(StatusCode.OK.getCode());
    return response;
  }

  public ApiResponse createLaunch(String body) {
    ApiResponse response = client.doPost(createUrl(LAUNCH_BASE_ENDPOINT), body);
    assertThat(response.getStatusCode()).isEqualTo(StatusCode.CREATED.getCode());
    return response;
  }

  public ApiResponse deleteLaunches(List<Integer> idList) {
    Map<String, List<Integer>> ids = Map.of("ids", idList);
    String body = convertToJson(ids);
    ApiResponse response = client.doDelete(createUrl(LAUNCH_BASE_ENDPOINT), body);
    assertThat(response.getStatusCode()).isEqualTo(StatusCode.OK.getCode());
    return response;
  }

  public ApiResponse finishLaunch(String uuid) {
    String body = getFileAsStringByPath("src/test/resources/data/api/finish_launch_body.json");
    return client.doPut(
        createUrlMultipleEndpoints(LAUNCH_BASE_ENDPOINT, uuid, "finish"),
        body);
  }

  public ApiResponse createAndStopLaunch(String body) {
    ApiResponse response = createLaunch(body);
    String launchUuid = readJsonSingleValue(response.getResponseBody(), "$.id");

    ApiResponse finishResponse = finishLaunch(launchUuid);
    assertThat(finishResponse.getStatusCode()).isEqualTo(StatusCode.OK.getCode());
    return response;
  }

}
