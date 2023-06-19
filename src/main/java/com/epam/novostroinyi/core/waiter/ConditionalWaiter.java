package com.epam.novostroinyi.core.waiter;

import com.epam.novostroinyi.core.ui.element.UiElement;
import com.epam.novostroinyi.core.ui.element.UiElementsCollection;
import org.openqa.selenium.WebElement;

public interface ConditionalWaiter {

  void visible(UiElement element);
  void visible(UiElementsCollection element);

  void clickable(UiElement element);
  void clickable(WebElement element);
}
