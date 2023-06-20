package com.epam.novostroinyi.ui.testng;

import static com.epam.novostroinyi.core.assertion.Assert.assertEquals;
import static com.epam.novostroinyi.core.assertion.Assert.assertHasSize;
import static com.epam.novostroinyi.core.constant.LaunchesConstants.ITEMS_FILTER_TEST_DATA_PATH;
import static com.epam.novostroinyi.core.constant.LaunchesConstants.LAUNCH_RESULTS_TEST_DATA_PATH;
import static com.epam.novostroinyi.core.util.FileUtils.convertCsvListOfArraysToMaps;
import static com.epam.novostroinyi.core.util.FileUtils.getContentFromFile;
import static com.epam.novostroinyi.core.util.FileUtils.getHtmlFieldContentsByText;
import static com.epam.novostroinyi.core.util.FileUtils.readCsvFile;
import static com.epam.novostroinyi.core.util.JsonUtils.readJsonListOfValues;
import static com.epam.novostroinyi.core.util.JsonUtils.readJsonToObject;

import com.epam.novostroinyi.api.step.LaunchesApiSteps;
import com.epam.novostroinyi.core.constant.StatusCode;
import com.epam.novostroinyi.core.constant.TestStatus;
import com.epam.novostroinyi.core.model.launch.LaunchDto;
import com.epam.novostroinyi.core.util.JsonUtils;
import com.epam.novostroinyi.ui.step.SidebarSteps;
import java.io.File;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.EnumUtils;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LaunchesTestNgTest extends BaseTestNgTest {

  @DataProvider(name = "getTestItemsData", parallel = true)
  private Object[][] getTestItemsData() {
    List<String[]> dataWithoutHeader = readCsvFile(ITEMS_FILTER_TEST_DATA_PATH, 1);
    return dataWithoutHeader.toArray(new String[][]{});
  }

  @DataProvider(name = "getLaunchStatistics", parallel = true)
  private Object[][] getLaunchStatistics() {
    return convertCsvListOfArraysToMaps(LAUNCH_RESULTS_TEST_DATA_PATH)
        .stream()
        .map(row -> new Object[]{row})
        .toArray(Object[][]::new);
  }

  @Test(dataProvider = "getTestItemsData")
  public void testItemsFilterTest(String filterPhrase, String expectedItems) {
    int itemId = 3;
    int expectedItemsCount = Integer.parseInt(expectedItems);
    int actualItemsListSize = new SidebarSteps()
        .openLaunches()
        .openLaunch(itemId)
        .applyFilter(filterPhrase)
        .getTestItems()
        .size();
    assertEquals(expectedItemsCount, actualItemsListSize);
  }

  @Test(dataProvider = "getLaunchStatistics")
  public void checkLaunchCorrectData(Map<String, String> launchExpectedData) {
    Map<String, String> launchStatistics = new SidebarSteps()
        .openLaunches()
        .getLaunchStatistics(Integer.parseInt(launchExpectedData.get("launchId")));

    SoftAssertions softAssert = new SoftAssertions();

    EnumUtils.getEnumList(TestStatus.class)
        .forEach(status -> softAssert.assertThat(launchStatistics.get(status.getStatus()))
            .isEqualTo(launchExpectedData.get(status.getStatus())));

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

    String actualLatestLaunchNumber = new SidebarSteps().openLaunches()
        .switchLatestLaunchFilter()
        .getLaunchNumbers()
        .get(0)
        .getText();

    assertEquals(actualLatestLaunchNumber, "#" + latestLaunch.getNumber());
  }

  @Test
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

    File exportedLaunch = new SidebarSteps().openLaunches().exportLaunch(randomLaunch.getNumber());

    String fileContent = getContentFromFile(exportedLaunch);

    List<String> htmlTextContent = getHtmlFieldContentsByText(fileContent, "span")
        .stream()
        .filter(content -> content.contains(randomLaunch.getName()))
        .toList();

    assertHasSize(htmlTextContent, 1);
  }
}
