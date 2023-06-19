package com.epam.novostroinyi.core.waiter;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.epam.novostroinyi.core.ui.element.UiElement;
import com.epam.novostroinyi.core.ui.element.UiElementsCollection;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.openqa.selenium.WebElement;

public class SelenideWaiter implements ConditionalWaiter {

  @Override
  public void visible(UiElement element) {
    $(element.getLocator()).shouldBe(Condition.visible, Duration.of(20, ChronoUnit.SECONDS));
  }

  @Override
  public void visible(UiElementsCollection element) {
    $(element.getLocator()).shouldBe(Condition.visible, Duration.of(20, ChronoUnit.SECONDS));
  }

  @Override
  public void clickable(UiElement element) {
    $(element.getLocator()).shouldBe(Condition.interactable, Duration.of(10, ChronoUnit.SECONDS));
  }

  @Override
  public void clickable(WebElement element) {
    $(element).shouldBe(Condition.interactable, Duration.of(10, ChronoUnit.SECONDS));
  }
}
