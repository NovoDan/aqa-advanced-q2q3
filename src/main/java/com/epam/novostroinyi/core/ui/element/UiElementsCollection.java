package com.epam.novostroinyi.core.ui.element;

import static com.epam.novostroinyi.core.util.WebDriverUtils.executeJs;

import com.codeborne.selenide.WebDriverRunner;
import java.util.AbstractList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UiElementsCollection extends AbstractList<WebElement> {

  @Getter
  private final By locator;
  private List<WebElement> elementList;

  private UiElementsCollection(By locator) {
    this.locator = locator;
    elementList = WebDriverRunner.getWebDriver().findElements(locator);
  }

  public void refreshElement() {
    elementList = new UiElementsCollection(locator);
  }

  @Override
  public WebElement get(int index) {
    return elementList.get(index);
  }

  @Override
  public int size() {
    return elementList.size();
  }

  public void scrollTo(int id) {
    executeJs("arguments[0].scrollIntoView(true);", elementList.get(id));
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
      return new UiElementsCollection(By.xpath(xpathLocator));
    }
  }
}
