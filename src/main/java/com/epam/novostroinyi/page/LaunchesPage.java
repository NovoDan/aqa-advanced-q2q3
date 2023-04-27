package com.epam.novostroinyi.page;

import com.epam.novostroinyi.core.ui.element.UiElementsCollection;
import com.epam.novostroinyi.core.ui.element.UiElementsCollection.GetElementsBy;

public class LaunchesPage extends Sidebar implements BasePage {

  private final UiElementsCollection launches =
      GetElementsBy.xpath("//div[contains(@class, 'gridRow__grid-row-wrapper')]");


}
