package com.epam.novostroinyi.core.util;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minidev.json.JSONArray;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtils {

  public static <T> T readJsonToObject(String json, Class<T> clazz) {
    return new Gson().fromJson(json, clazz);
  }

  public static <T> String convertToJson(T obj) {
    return new Gson().toJson(obj);
  }

  public static String readJsonSingleValue(String json, String jsonPath) {
    if (Objects.isNull(json)) {
      return StringUtils.EMPTY;
    }
    var result = JsonPath.parse(json).read(jsonPath);
    if (result instanceof JSONArray arrayResult) {
      return arrayResult.isEmpty() ? StringUtils.EMPTY : convertToJson(arrayResult.get(0));
    }
    return result.toString();
  }

  public static List readJsonListOfValues(String json, String jsonPath) {
    if (Objects.isNull(json)) {
      return new JSONArray();
    }
    return JsonPath.parse(json).read(jsonPath);
  }

}
