package com.epam.novostroinyi.api;

import static com.epam.novostroinyi.api.step.LaunchesUtils.filterLaunchesById;
import static com.epam.novostroinyi.api.step.LaunchesUtils.filterLaunchesByParam;
import static com.epam.novostroinyi.core.util.FileUtils.getFileAsStringByPath;
import static com.epam.novostroinyi.core.util.JsonUtils.convertToJson;
import static com.epam.novostroinyi.core.util.JsonUtils.readJsonListOfValues;
import static com.epam.novostroinyi.core.util.JsonUtils.readJsonSingleValue;
import static com.epam.novostroinyi.core.util.JsonUtils.readJsonToObject;
import static org.assertj.core.api.Assertions.assertThat;

import com.epam.novostroinyi.api.step.LaunchesApiSteps;
import com.epam.novostroinyi.core.client.api.ApiResponse;
import com.epam.novostroinyi.core.model.launch.LaunchDto;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;

public class LaunchesTest {

  @Test
  public void listLaunchesTest() {
    int expectedLaunchesNumber = 5;
    String response = new LaunchesApiSteps().getLaunchesList().getResponseBody();
    List<?> jsonLaunchesList = readJsonListOfValues(response, "$.content");
    assertThat(jsonLaunchesList).hasSize(expectedLaunchesNumber);
  }

  @Nested
  public class LaunchCreatingTest {

    static List<Integer> launchIds = new ArrayList<>();
    static LaunchesApiSteps steps = new LaunchesApiSteps();

    private String uuid;

    @AfterEach
    public void addLaunchToDeleteList() {
      String allLaunches = steps.getLaunchesList().getResponseBody();

      String launch = filterLaunchesByParam(allLaunches, "uuid", uuid);
      steps.finishLaunch(uuid);
      launchIds.add(Integer.parseInt(readJsonSingleValue(launch, "$.id")));
    }

    @AfterAll
    public static void deleteLaunch(){
      steps.deleteLaunches(launchIds);
    }

    @Test
    public void createLaunchTest() {
      String response = steps.getLaunchesList().getResponseBody();
      List<?> launchesListBefore = readJsonListOfValues(response, "$.content");

      String launchJsonTemplate = getFileAsStringByPath("src/test/resources/data/api/launch_template.json");
      LaunchDto launch = readJsonToObject(launchJsonTemplate, LaunchDto.class);
      launch.setDescription("API Test / Create");
      String body = convertToJson(launch);

      ApiResponse createLaunchResponse = steps.createLaunch(body);

      uuid = readJsonSingleValue(createLaunchResponse.getResponseBody(), "$.id");

      List<?> launchesListAfter = readJsonListOfValues(steps.getLaunchesList().getResponseBody(), "$.content");

      assertThat(launchesListAfter).hasSizeGreaterThan(launchesListBefore.size());
    }
  }

  @Nested
  public class LaunchDeletingTest {
    LaunchesApiSteps steps = new LaunchesApiSteps();
    ApiResponse createLaunchResponse;

    @BeforeEach
    public void createLaunch() {
      String launchJsonTemplate = getFileAsStringByPath("src/test/resources/data/api/launch_template.json");
      LaunchDto launch = readJsonToObject(launchJsonTemplate, LaunchDto.class);
      launch.setDescription("API Test / Delete");
      String body = convertToJson(launch);

      createLaunchResponse = steps.createAndStopLaunch(body);
    }

    @Test
    public void deleteLaunch() {
      int launchNumber = Integer.parseInt(readJsonSingleValue(createLaunchResponse.getResponseBody(), "$.number"));
      String launchesList = steps.getLaunchesList().getResponseBody();
      String createdLaunch = filterLaunchesByParam(launchesList, "number", launchNumber);
      int launchId = Integer.parseInt(readJsonSingleValue(createdLaunch, "$.id"));

      steps.deleteLaunches(List.of(launchId));

      String updatedLaunchList = steps.getLaunchesList().getResponseBody();

      List<?> jsonLaunchesList = readJsonListOfValues(launchesList, "$.content");
      List<?> jsonUpdatedLaunchesList = readJsonListOfValues(updatedLaunchList, "$.content");

      String expectedLaunch = filterLaunchesById(updatedLaunchList, launchId);

      assertThat(jsonUpdatedLaunchesList).as("Launch was not deleted").hasSizeLessThan(jsonLaunchesList.size());
      assertThat(expectedLaunch).as("Wrong launch was deleted").isEqualTo(StringUtils.EMPTY);
    }
  }
}
