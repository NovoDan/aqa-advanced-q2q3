package com.epam.novostroinyi.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.novostroinyi.api.step.LaunchesApiSteps;
import com.epam.novostroinyi.core.util.JsonUtils;
import net.minidev.json.JSONArray;
import org.testng.annotations.Test;

public class LaunchesTest {

  @Test
  public void listLaunchesTest() {
    int expectedLaunchesNumber = 5;
    String response = new LaunchesApiSteps().getLaunchesList().getResponseBody();
    JSONArray jsonLaunchesList = JsonUtils.readJsonListOfValues(response, "$.content");
    assertThat(jsonLaunchesList).hasSize(expectedLaunchesNumber);
  }

}
