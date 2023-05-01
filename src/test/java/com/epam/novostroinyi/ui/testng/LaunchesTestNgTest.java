package com.epam.novostroinyi.ui.testng;

import static com.epam.novostroinyi.core.util.CommonUtils.readCsvFile;
import static org.testng.AssertJUnit.assertEquals;

import com.epam.novostroinyi.step.LaunchesSteps;
import java.util.List;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LaunchesTestNgTest extends BaseTestNgTest {

  @DataProvider(name = "getTestData", parallel = true)
  private Object[][] getTestData() {
    String filePath = "src/test/resources/data/ui/ItemsFilterData.csv";
    List<String[]> dataWithoutHeader = readCsvFile(filePath);
    return dataWithoutHeader.toArray(new String[][] {});
  }

  @Test(dataProvider = "getTestData")
  public void testItemsFilterTest(String filterPhrase, String expectedItems) {
    int itemId = 3;
    int expectedItemsCount = Integer.parseInt(expectedItems);
    int actualItemsListSize = new LaunchesSteps()/*.switchSortingItemsByName()*/
        .openLaunch(itemId)
        .applyFilter(filterPhrase)
        .getTestItems()
        .size();
    assertEquals(expectedItemsCount, actualItemsListSize);
  }
}
