package com.epam.novostroinyi.ui.junit;

import static org.testng.AssertJUnit.assertEquals;

import com.epam.novostroinyi.step.LaunchesSteps;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class LaunchesJUnitTest extends BaseJUnitTest {

  @ParameterizedTest
  @CsvFileSource(resources = "/data/ui/ItemsFilterData.csv", numLinesToSkip = 1)
  public void testItemsFilterTest(String filterPhrase, int expectedItems) {
    int itemId = 3;
    int actualItemsListSize = new LaunchesSteps()/*.switchSortingItemsByName()*/
        .openLaunch(itemId)
        .applyFilter(filterPhrase)
        .getTestItems()
        .size();
    assertEquals(expectedItems, actualItemsListSize);
  }
}
