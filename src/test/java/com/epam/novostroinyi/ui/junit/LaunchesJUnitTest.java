package com.epam.novostroinyi.ui.junit;

import static com.epam.novostroinyi.core.util.FileUtils.convertCsvListOfArraysToMaps;
import static org.testng.AssertJUnit.assertEquals;

import com.epam.novostroinyi.ui.step.LaunchesSteps;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

public class LaunchesJUnitTest extends BaseJUnitTest {

  @ParameterizedTest
  @CsvFileSource(resources = "/data/ui/ItemsFilterData.csv", numLinesToSkip = 1)
  public void testItemsFilterTest(String filterPhrase, int expectedItems) {
    int itemId = 3;
    int actualItemsListSize = new LaunchesSteps()
        .openLaunch(itemId)
        .applyFilter(filterPhrase)
        .getTestItems()
        .size();
    assertEquals(expectedItems, actualItemsListSize);
  }

  @ParameterizedTest
  @MethodSource("getLaunchStatistics")
  public void checkLaunchCorrectData(Map<String, String> launchExpectedData) {
    Map<String, String> launchStatistics = new LaunchesSteps()
        .getLaunchStatistics(Integer.parseInt(launchExpectedData.get("launchId")));

    SoftAssertions softAssert = new SoftAssertions();

    launchStatistics.keySet().forEach(
        key -> softAssert.assertThat(launchStatistics.get(key))
            .isEqualTo(launchExpectedData.get(key)));

    softAssert.assertAll();
  }

  private static Stream<Map<String, String>> getLaunchStatistics() {
    return convertCsvListOfArraysToMaps("src/test/resources/data/ui/LaunchResultsData.csv").stream();
  }
}
