package com.epam.novostroinyi.core.util;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
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

  public static String readJsonSingleValue(String json, String jsonPath) {
    if(Objects.isNull(json)) {
      return StringUtils.EMPTY;
    }
    return JsonPath.parse(json).read(jsonPath).toString();
  }

  public static JSONArray readJsonListOfValues(String json, String jsonPath) {
    if(Objects.isNull(json)) {
      return new JSONArray();
    }
    return JsonPath.parse(json).read(jsonPath);
  }

}
