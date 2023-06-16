package com.epam.novostroinyi.ui.page;

import com.epam.novostroinyi.core.ui.element.UiElementsCollection;
import com.epam.novostroinyi.core.ui.element.UiElementsCollection.GetElementsBy;
import lombok.Getter;

@Getter
public class DashboardPage extends BasePage {

  private final UiElementsCollection draggableWidgets = GetElementsBy.xpath(
      "//div[contains(@class, 'widget-header') and contains(@class, 'draggable')]");

  public DashboardPage() {
    waitForPageLoading(draggableWidgets.getLocator());
  }
}

