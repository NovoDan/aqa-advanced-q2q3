package com.epam.novostroinyi.core.notification;

import com.epam.novostroinyi.core.client.api.ApiClient;
import com.epam.novostroinyi.core.client.api.ApiResponse;
import com.epam.novostroinyi.core.client.api.RestClient;
import com.epam.novostroinyi.core.config.Property;
import com.epam.novostroinyi.core.constant.StatusCode;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class TestRailNotifier implements Notifier {

  Map<String, String> requestParams = new HashMap<>();

  private static String getAuthorization(String user, String password) {
    try {
      return new String(Base64.getEncoder().encode((user + ":" + password).getBytes()));
    } catch (IllegalArgumentException e) {
      return StringUtils.EMPTY;
    }
  }

  @Override
  public void sendMessage(Map<String, String> params) {
    String auth = getAuthorization(Property.COMMON_PROPERTY.testRailApiLogin(),
        Property.SECRET_PROPERTY.testRailApiPassword());
    Map<String, String> headers = Map.of(
        "Authorization", "Basic " + auth,
        "Content-Type", "application/json",
        "Accept", "application/json");
    ApiClient client = new RestClient(headers);

    requestParams.put("status_id", params.get("statusId"));
    requestParams.put("comment", params.get("message"));

    String url = Property.COMMON_PROPERTY.testRailApiUrl() + "add_result_for_case/1/" + params.get("caseId");
    ApiResponse response = client.doPost( url, requestParams);
    response.verifyStatusCode(StatusCode.OK);
  }
}
