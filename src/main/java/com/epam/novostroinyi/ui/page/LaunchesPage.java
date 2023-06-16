package com.epam.novostroinyi.ui.page;

import com.epam.novostroinyi.core.ui.element.UiElement;
import com.epam.novostroinyi.core.ui.element.UiElement.GetElementBy;
import com.epam.novostroinyi.core.ui.element.UiElementsCollection;
import com.epam.novostroinyi.core.ui.element.UiElementsCollection.GetElementsBy;
import lombok.Getter;

@Getter
public class LaunchesPage extends BasePage {

  private final UiElementsCollection launchesList =
      GetElementsBy.xpath(
          "//div[contains(@class, 'gridRow__grid-row-wrapper')]//td//div[contains(@class, 'itemInfo__main-info')]");

  private final UiElementsCollection launchesTableHeader =
      GetElementsBy.xpath("//div[contains(@class, 'headerCell__sorting')]/div");

  private final UiElementsCollection launchesTotalColumn = GetElementsBy.xpath("//div[contains(@class, 'launchSuiteGrid__total')]");
  private final UiElementsCollection launchesPassedColumn = GetElementsBy.xpath("//div[contains(@class, 'launchSuiteGrid__passed')]");
  private final UiElementsCollection launchesFailedColumn = GetElementsBy.xpath("//div[contains(@class, 'launchSuiteGrid__failed')]");
  private final UiElementsCollection launchesSkippedColumn = GetElementsBy.xpath("//div[contains(@class, 'launchSuiteGrid__skipped')]");
  private final UiElementsCollection productBugColumn = GetElementsBy.xpath("//div[contains(@class, 'launchSuiteGrid__pb-col')]");
  private final UiElementsCollection productAutoBugColumn = GetElementsBy.xpath("//div[contains(@class, 'launchSuiteGrid__ab-col')]");
  private final UiElementsCollection productSystemIssueColumn = GetElementsBy.xpath("//div[contains(@class, 'launchSuiteGrid__si-col')]");
  private final UiElementsCollection productToInvestigateColumn = GetElementsBy.xpath("//div[contains(@class, 'launchSuiteGrid__ti-col')]");
  private final UiElement allLatestLaunchesFilter = GetElementBy.xpath("//div[contains(@class, 'allLatestDropdown__arrow')]");
  private final UiElementsCollection allLatestLaunchesDropdownOptions = GetElementsBy.xpath("//div[contains(@class, 'allLatestDropdown__option-list')]/div");
  private final UiElementsCollection launchNumbers = GetElementsBy.xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[1]/div[1]/div[1]/div[2]/div/div[2]/div[2]/div[2]/div[2]/td[2]/div/div[1]/span/a");

  public LaunchesPage() {
    waitForPageLoading(launchesList.getLocator());
  }
}
