package com.epam.novostroinyi.ui.page;

import com.epam.novostroinyi.core.ui.element.UiElement;
import com.epam.novostroinyi.core.ui.element.UiElementsCollection;
import com.epam.novostroinyi.core.util.WebDriverUtils;

public abstract class BasePage {

  protected void waitForPageLoading(UiElement element) {
    WebDriverUtils.getWaiter().visible(element);
  }

  protected void waitForPageLoading(UiElementsCollection element) {
    WebDriverUtils.getWaiter().visible(element);
  }
}
