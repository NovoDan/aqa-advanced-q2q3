package com.epam.novostroinyi.ui.page;

import com.epam.novostroinyi.core.ui.element.UiElement;
import com.epam.novostroinyi.core.ui.element.UiElement.GetElementBy;
import com.epam.novostroinyi.core.ui.element.UiElementsCollection;
import com.epam.novostroinyi.core.ui.element.UiElementsCollection.GetElementsBy;
import lombok.Getter;

@Getter
public class TestItemsPage extends BasePage {

  private final UiElementsCollection items = GetElementsBy.xpath("//td//div/a[contains(@class,'itemInfo__name-link')]");
  private final UiElement search = GetElementBy.xpath("//input[contains(@class,\"inputConditional\")]");

  public TestItemsPage() {
    waitForPageLoading(items.getLocator());
  }
}
