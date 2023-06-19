package com.epam.novostroinyi.ui.testng;

import static com.epam.novostroinyi.core.constant.LaunchesConstants.ITEMS_FILTER_TEST_DATA_PATH;
import static com.epam.novostroinyi.core.constant.LaunchesConstants.LAUNCH_RESULTS_TEST_DATA_PATH;
import static com.epam.novostroinyi.core.util.FileUtils.convertCsvListOfArraysToMaps;
import static com.epam.novostroinyi.core.util.FileUtils.readCsvFile;
import static org.testng.AssertJUnit.assertEquals;

import com.epam.novostroinyi.core.constant.TestStatus;
import com.epam.novostroinyi.ui.step.LaunchesSteps;
import com.epam.novostroinyi.ui.step.SidebarSteps;
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
}
