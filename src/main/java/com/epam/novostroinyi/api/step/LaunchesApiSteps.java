package com.epam.novostroinyi.api.step;

import static com.epam.novostroinyi.core.util.FileUtils.getFileAsStringByPath;
import static com.epam.novostroinyi.core.util.JsonUtils.convertToJson;
import static com.epam.novostroinyi.core.util.JsonUtils.readJsonSingleValue;

import com.epam.novostroinyi.core.client.api.ApiClient;
import com.epam.novostroinyi.core.client.api.ApiResponse;
import com.epam.novostroinyi.core.config.ConfigUtils;
import java.util.List;
import java.util.Map;

public class LaunchesApiSteps extends BaseApiSteps {

  private static final String LAUNCH_BASE_ENDPOINT = "launch";
  private final ApiClient client = ConfigUtils.getClient();

  public ApiResponse getLaunchesList() {
    return client.doGet(createUrl(LAUNCH_BASE_ENDPOINT));
  }

  public ApiResponse createLaunch(String body) {
    return client.doPost(createUrl(LAUNCH_BASE_ENDPOINT), body);
  }

  public ApiResponse deleteLaunches(List<Integer> idList) {
    Map<String, List<Integer>> ids = Map.of("ids", idList);
    String body = convertToJson(ids);
    return client.doDelete(createUrl(LAUNCH_BASE_ENDPOINT), body);
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

    return finishLaunch(launchUuid);
  }

  public ApiResponse getLaunchByUuid(String uuid) {
    return client.doGet(
        createUrlMultipleEndpoints(LAUNCH_BASE_ENDPOINT, "uuid", uuid));
  }

  public ApiResponse updateLaunch(int launchId, String body) {
    return client.doPut(
        createUrlMultipleEndpoints(LAUNCH_BASE_ENDPOINT, String.valueOf(launchId), "update"), body);
  }

  public ApiResponse exportLaunchReport(int launchId, String reportExtension) {
    return client.doGet(
        createUrlMultipleEndpoints(LAUNCH_BASE_ENDPOINT, String.valueOf(launchId), "report"),
        Map.of("view", reportExtension));

  }
}
