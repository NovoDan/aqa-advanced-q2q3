package com.epam.novostroinyi.core.ui.element;

import com.codeborne.selenide.WebDriverRunner;
import java.util.AbstractList;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UiElementsCollection extends AbstractList<WebElement> {

  List<WebElement> elementList;

  private UiElementsCollection(By locator) {
    elementList = WebDriverRunner.getWebDriver().findElements(locator);
  }

  @Override
  public WebElement get(int index) {
    return elementList.get(index);
  }

  @Override
  public int size() {
    return elementList.size();
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class GetElementsBy {

    public static UiElementsCollection name(String byName) {
      return new UiElementsCollection(By.name(byName));
    }

    public static UiElementsCollection id(String byId) {
      return new UiElementsCollection(By.id(byId));
    }

    public static UiElementsCollection className(String byClassName) {
      return new UiElementsCollection(By.className(byClassName));
    }

    public static UiElementsCollection text(String byText) {
      return new UiElementsCollection(By.name(byText));
    }

    public static UiElementsCollection xpath(String xpathLocator) {
      return new UiElementsCollection(By.name(xpathLocator));
    }
  }
}