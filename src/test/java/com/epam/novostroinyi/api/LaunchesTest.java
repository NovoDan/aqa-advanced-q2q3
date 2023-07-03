package com.epam.novostroinyi.api;

import static com.epam.novostroinyi.api.step.LaunchesUtils.filterLaunchesById;
import static com.epam.novostroinyi.api.step.LaunchesUtils.filterLaunchesByParam;
import static com.epam.novostroinyi.core.assertion.Assert.assertEquals;
import static com.epam.novostroinyi.core.assertion.Assert.assertTrue;
import static com.epam.novostroinyi.core.util.FileUtils.getFileAsStringByPath;
import static com.epam.novostroinyi.core.util.FileUtils.saveByteArrayToFile;
import static com.epam.novostroinyi.core.util.JsonUtils.convertToJson;
import static com.epam.novostroinyi.core.util.JsonUtils.readJsonListOfValues;
import static com.epam.novostroinyi.core.util.JsonUtils.readJsonSingleValue;
import static com.epam.novostroinyi.core.util.JsonUtils.readJsonToObject;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;

import com.epam.novostroinyi.api.step.LaunchesApiSteps;
import com.epam.novostroinyi.core.annotation.CaseId;
import com.epam.novostroinyi.core.client.api.ApiResponse;
import com.epam.novostroinyi.core.constant.StatusCode;
import com.epam.novostroinyi.core.model.launch.LaunchDto;
import com.epam.novostroinyi.core.model.launch.LaunchDto.Attribute;
import com.epam.novostroinyi.core.util.JsonUtils;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class LaunchesTest {

  @Test
  @Order(1)
  public void listLaunchesTest() {
    int expectedLaunchesNumber = 5;
    String response = new LaunchesApiSteps().getLaunchesList()
        .verifyStatusCode(StatusCode.OK)
        .getResponseBody();
    List<?> jsonLaunchesList = readJsonListOfValues(response, "$.content");
    assertThat(jsonLaunchesList).hasSize(expectedLaunchesNumber);
  }

  @Test
  @CaseId(caseId = "1")
  public void getLaunchByUuid() {
    String launchListResponse = new LaunchesApiSteps().getLaunchesList()
        .verifyStatusCode(StatusCode.OK)
        .getResponseBody();
    List<?> jsonLaunchesList = readJsonListOfValues(launchListResponse, "$.content");
    LaunchDto randomLaunch = jsonLaunchesList.stream()
        .map(LinkedHashMap.class::cast)
        .map(JsonUtils::convertToJson)
        .map(launch -> readJsonToObject(launch, LaunchDto.class))
        .findAny()
        .orElseThrow();

    String getLaunchResponse = new LaunchesApiSteps().getLaunchByUuid(randomLaunch.getUuid())
        .getResponseBody();

    assertEquals(readJsonToObject(getLaunchResponse, LaunchDto.class), randomLaunch);
  }

  @Test
  @CaseId(caseId = "2")
  public void exportLaunchTest() {
    LaunchesApiSteps steps = new LaunchesApiSteps();
    String launchListResponse = steps.getLaunchesList()
        .verifyStatusCode(StatusCode.OK)
        .getResponseBody();
    List<?> jsonLaunchesList = readJsonListOfValues(launchListResponse, "$.content");
    LaunchDto randomLaunch = jsonLaunchesList.stream()
        .map(LinkedHashMap.class::cast)
        .map(JsonUtils::convertToJson)
        .map(launch -> readJsonToObject(launch, LaunchDto.class))
        .findAny()
        .orElseThrow();

    String pathToFile = ".\\output";
    String fileName = "report.xls";

    ApiResponse exportResponse = steps.exportLaunchReport(randomLaunch.getId(), "xls")
        .verifyStatusCode(StatusCode.OK);
    saveByteArrayToFile(exportResponse.getFile(), pathToFile, fileName);
    Pattern pattern = Pattern.compile("Launch name.*(?:%s)".formatted(randomLaunch.getName()));
    Matcher matcher = pattern.matcher(new String(exportResponse.getFile(), StandardCharsets.UTF_8));

    File exportedReport = new File(pathToFile, fileName);

    assertTrue(matcher.find());
    assertTrue(exportedReport.exists());
  }

  @Nested
  public class LaunchCreatingTest {

    static List<Integer> launchIds = new ArrayList<>();
    static LaunchesApiSteps steps = new LaunchesApiSteps();

    private String uuid;

    @AfterAll
    public static void deleteLaunch() {
      steps.deleteLaunches(launchIds).verifyStatusCode(StatusCode.OK);
    }

    @AfterEach
    public void addLaunchToDeleteList() {
      String launchToDelete = steps.getLaunchByUuid(uuid).getResponseBody();
      steps.finishLaunch(uuid).verifyStatusCode(StatusCode.OK);
      launchIds.add(Integer.parseInt(readJsonSingleValue(launchToDelete, "$.id")));
    }

    @Test
    @CaseId(caseId = "4")
    public void createLaunchTest() {
      String response = steps.getLaunchesList()
          .verifyStatusCode(StatusCode.OK)
          .getResponseBody();
      List<?> launchesListBefore = readJsonListOfValues(response, "$.content");

      String launchJsonTemplate = getFileAsStringByPath(
          "src/test/resources/data/api/launch_template.json");
      LaunchDto launch = readJsonToObject(launchJsonTemplate, LaunchDto.class);
      launch.setDescription("API Test / Create");
      String body = convertToJson(launch);

      ApiResponse createLaunchResponse = steps.createLaunch(body)
          .verifyStatusCode(StatusCode.CREATED);

      uuid = readJsonSingleValue(createLaunchResponse.getResponseBody(), "$.id");

      List<?> launchesListAfter = readJsonListOfValues(steps.getLaunchesList().getResponseBody(),
          "$.content");

      assertThat(launchesListAfter).hasSizeGreaterThan(launchesListBefore.size());
    }

    @Test
    @CaseId(caseId = "5")
    public void updateLaunchTest() {
      String launchJsonTemplate = getFileAsStringByPath(
          "src/test/resources/data/api/launch_template.json");
      LaunchDto launch = readJsonToObject(launchJsonTemplate, LaunchDto.class);
      launch.setDescription("API Test / Create");
      String body = convertToJson(launch);

      ApiResponse createLaunchResponse = steps.createLaunch(body)
          .verifyStatusCode(StatusCode.CREATED);
      uuid = readJsonSingleValue(createLaunchResponse.getResponseBody(), "$.id");
      String createdLaunch = steps.getLaunchByUuid(uuid).verifyStatusCode(StatusCode.OK)
          .getResponseBody();

      int launchId = Integer.parseInt(readJsonSingleValue(createdLaunch, "$.id"));
      LaunchDto launchUpdateDto = readJsonToObject(createdLaunch, LaunchDto.class);
      launchUpdateDto.setDescription("Changed description");
      Attribute attribute = new LaunchDto.Attribute();
      attribute.setKey("customkey");
      attribute.setValue(randomAlphabetic(5));
      launchUpdateDto.setAttributes(List.of(attribute));

      steps.updateLaunch(launchId, convertToJson(launchUpdateDto))
          .verifyStatusCode(StatusCode.OK);

      ApiResponse updatedLaunchResponse = steps.getLaunchByUuid(uuid)
          .verifyStatusCode(StatusCode.OK);
      LaunchDto updatedLaunch = readJsonToObject(updatedLaunchResponse.getResponseBody(),
          LaunchDto.class);

      assertEquals(updatedLaunch.getDescription(), launchUpdateDto.getDescription());
      assertEquals(updatedLaunch.getAttributes(), launchUpdateDto.getAttributes());
    }
  }

  @Nested
  public class LaunchDeletingTest {

    LaunchesApiSteps steps = new LaunchesApiSteps();
    ApiResponse createLaunchResponse;

    @BeforeEach
    public void createLaunch() {
      String launchJsonTemplate = getFileAsStringByPath(
          "src/test/resources/data/api/launch_template.json");
      LaunchDto launch = readJsonToObject(launchJsonTemplate, LaunchDto.class);
      launch.setDescription("API Test / Delete");
      String body = convertToJson(launch);

      createLaunchResponse = steps.createAndStopLaunch(body).verifyStatusCode(StatusCode.OK);
    }

    @Test
    @CaseId(caseId = "3")
    public void deleteLaunch() {
      int launchNumber = Integer.parseInt(
          readJsonSingleValue(createLaunchResponse.getResponseBody(), "$.number"));
      String launchesList = steps.getLaunchesList().verifyStatusCode(StatusCode.OK)
          .getResponseBody();
      String createdLaunch = filterLaunchesByParam(launchesList, "number", launchNumber);
      int launchId = Integer.parseInt(readJsonSingleValue(createdLaunch, "$.id"));

      steps.deleteLaunches(List.of(launchId)).verifyStatusCode(StatusCode.OK);

      String updatedLaunchList = steps.getLaunchesList().verifyStatusCode(StatusCode.OK)
          .getResponseBody();

      List<?> jsonLaunchesList = readJsonListOfValues(launchesList, "$.content");
      List<?> jsonUpdatedLaunchesList = readJsonListOfValues(updatedLaunchList, "$.content");

      String expectedLaunch = filterLaunchesById(updatedLaunchList, launchId);

      assertThat(jsonUpdatedLaunchesList).as("Launch was not deleted")
          .hasSizeLessThan(jsonLaunchesList.size());
      assertThat(expectedLaunch).as("Wrong launch was deleted").isEqualTo(StringUtils.EMPTY);
    }
  }
}
