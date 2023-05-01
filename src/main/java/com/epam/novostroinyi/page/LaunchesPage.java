package com.epam.novostroinyi.page;

import com.epam.novostroinyi.core.ui.element.UiElementsCollection;
import com.epam.novostroinyi.core.ui.element.UiElementsCollection.GetElementsBy;
import lombok.Getter;

@Getter
public class LaunchesPage extends Sidebar implements BasePage {

  private final UiElementsCollection launchesList =
      GetElementsBy.xpath(
          "//div[contains(@class, 'gridRow__grid-row-wrapper')]//td//div[contains(@class, 'itemInfo__main-info')]");

  private final UiElementsCollection launchesTableHeader =
      GetElementsBy.xpath("//div[contains(@class, 'headerCell__sorting')]/div");
}
