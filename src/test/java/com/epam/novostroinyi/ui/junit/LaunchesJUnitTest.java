package com.epam.novostroinyi.ui.junit;

import static com.epam.novostroinyi.core.assertion.Assert.assertEquals;
import static com.epam.novostroinyi.core.util.FileUtils.convertCsvListOfArraysToMaps;
import static com.epam.novostroinyi.core.util.JsonUtils.readJsonListOfValues;
import static com.epam.novostroinyi.core.util.JsonUtils.readJsonToObject;

import com.epam.novostroinyi.api.step.LaunchesApiSteps;
import com.epam.novostroinyi.core.constant.StatusCode;
import com.epam.novostroinyi.core.model.launch.LaunchDto;
import com.epam.novostroinyi.core.util.JsonUtils;
import com.epam.novostroinyi.ui.step.SidebarSteps;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

public class LaunchesJUnitTest extends BaseJUnitTest {

  @ParameterizedTest
  @CsvFileSource(resources = "/data/ui/ItemsFilterData.csv", numLinesToSkip = 1)
  public void testItemsFilterTest(String filterPhrase, int expectedItems) {
    int itemId = 3;
    int actualItemsListSize = new SidebarSteps()
        .openLaunches()
        .openLaunch(itemId)
        .applyFilter(filterPhrase)
        .getTestItems()
        .size();
    assertEquals(expectedItems, actualItemsListSize);
  }

  @ParameterizedTest
  @MethodSource("getLaunchStatistics")
  public void checkLaunchCorrectData(Map<String, String> launchExpectedData) {
    Map<String, String> launchStatistics = new SidebarSteps()
        .openLaunches()
        .getLaunchStatistics(Integer.parseInt(launchExpectedData.get("launchId")));

    SoftAssertions softAssert = new SoftAssertions();

    launchStatistics.keySet().forEach(
        key -> softAssert.assertThat(launchStatistics.get(key))
            .isEqualTo(launchExpectedData.get(key)));

    softAssert.assertAll();
  }

  @Test
  public void getLatestLaunchTest() {
    LaunchesApiSteps steps = new LaunchesApiSteps();
    String launchListResponse = steps.getLaunchesList()
        .verifyStatusCode(StatusCode.OK)
        .getResponseBody();
    List<?> jsonLaunchesList = readJsonListOfValues(launchListResponse, "$.content");
    LaunchDto latestLaunch = jsonLaunchesList.stream()
        .map(LinkedHashMap.class::cast)
        .map(JsonUtils::convertToJson)
        .map(launch -> readJsonToObject(launch, LaunchDto.class))
        .max(Comparator.comparing(LaunchDto::getEndTime))
        .orElseThrow();

    var res = new SidebarSteps().openLaunches().switchLatestLaunchFilter().getLaunchNumbers().get(0).getText();

    System.out.println(res);

  }

  private static Stream<Map<String, String>> getLaunchStatistics() {
    return convertCsvListOfArraysToMaps("src/test/resources/data/ui/LaunchResultsData.csv").stream();
  }
}
