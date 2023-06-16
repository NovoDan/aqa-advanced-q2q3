package com.epam.novostroinyi.ui.page;

import com.epam.novostroinyi.core.ui.element.UiElementsCollection;
import com.epam.novostroinyi.core.ui.element.UiElementsCollection.GetElementsBy;
import lombok.Getter;

@Getter
public class DashboardsPage extends BasePage {

  private final UiElementsCollection dashboardTable = GetElementsBy.xpath("//a[contains(@class, 'dashboardTable')]");

  public DashboardsPage() {
    waitForPageLoading(dashboardTable.getLocator());
  }

}
