package com.epam.novostroinyi.core.ui.element;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.actions;

import com.epam.novostroinyi.core.config.ConfigUtils;
import com.epam.novostroinyi.core.logger.ILogger;
import java.util.AbstractList;
import java.util.ArrayList;
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

  private ILogger logger = ConfigUtils.getLogger();

  private UiElementsCollection(By locator) {
    this.locator = locator;
    elementList = new ArrayList<>($$ (locator));
  }

  public UiElementsCollection refreshElement() {
    logger.debug("Refreshing element container");
    elementList = new UiElementsCollection(locator);
    return this;
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
    logger.debug("Scrolling to element with id '{}'", id);
    actions().scrollToElement(elementList.get(id)).perform();
//    new WebActions(getWebDriver()).scrollToElement(elementList.get(id));
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
