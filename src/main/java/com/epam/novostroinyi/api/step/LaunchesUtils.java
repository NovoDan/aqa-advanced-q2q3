package com.epam.novostroinyi.api.step;

import static com.epam.novostroinyi.core.util.JsonUtils.readJsonSingleValue;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LaunchesUtils {

  public static String filterLaunchesById(String listLaunchesResponse, int id) {
    return filterLaunchesByParam(listLaunchesResponse, "id", id);
  }

  public static String filterLaunchesByParam(String json, String param, Object value) {
    String jsonPathFormat = "$.content[?(@.%s == '%s')]";
    return readJsonSingleValue(json, String.format(jsonPathFormat, param, value));
  }
}
