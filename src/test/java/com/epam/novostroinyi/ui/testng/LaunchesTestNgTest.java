package com.epam.novostroinyi.ui.testng;

import static com.epam.novostroinyi.core.util.CommonUtils.convertCsvListOfArraysToMaps;
import static com.epam.novostroinyi.core.util.CommonUtils.readCsvFile;
import static org.testng.AssertJUnit.assertEquals;

import com.epam.novostroinyi.step.LaunchesSteps;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LaunchesTestNgTest extends BaseTestNgTest {

  @DataProvider(name = "getTestItemsData", parallel = true)
  private Object[][] getTestItemsData() {
    String filePath = "src/test/resources/data/ui/ItemsFilterData.csv";
    List<String[]> dataWithoutHeader = readCsvFile(filePath, 1);
    return dataWithoutHeader.toArray(new String[][]{});
  }

  @DataProvider(name = "getLaunchStatistics", parallel = true)
  private Object[][] getLaunchStatistics() {
    return convertCsvListOfArraysToMaps("src/test/resources/data/ui/LaunchResultsData.csv")
        .stream()
        .map(row -> new Object[]{row})
        .toArray(Object[][]::new);
  }

  @Test(dataProvider = "getTestItemsData")
  public void testItemsFilterTest(String filterPhrase, String expectedItems) {
    int itemId = 3;
    int expectedItemsCount = Integer.parseInt(expectedItems);
    int actualItemsListSize = new LaunchesSteps()
        .openLaunch(itemId)
        .applyFilter(filterPhrase)
        .getTestItems()
        .size();
    assertEquals(expectedItemsCount, actualItemsListSize);
  }

  @Test(dataProvider = "getLaunchStatistics")
  public void checkLaunchCorrectData(Map<String, String> launchExpectedData) {
    Map<String, String> launchStatistics = new LaunchesSteps()
        .getLaunchStatistics(Integer.parseInt(launchExpectedData.get("launchId")));

    SoftAssertions softAssert = new SoftAssertions();

    launchStatistics.keySet().forEach(
        key -> softAssert.assertThat(launchStatistics.get(key))
            .isEqualTo(launchExpectedData.get(key)));

    softAssert.assertAll();
  }
}
