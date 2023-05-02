package com.epam.novostroinyi.core.ui.element;


import static com.codeborne.selenide.Selenide.element;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class UiElement implements WebElement {

  private final WebElement element;

  @Getter
  private final By locator;

  private UiElement(By locator) {
    this.locator = locator;
    element = element(locator);
  }

  @Override
  public void click() {
    element.click();
  }

  @Override
  public void submit() {
    element.submit();
  }

  @Override
  public void sendKeys(CharSequence... keysToSend) {
    element.sendKeys(keysToSend);
  }

  @Override
  public void clear() {
    element.clear();
  }

  @Override
  public String getTagName() {
    return element.getTagName();
  }

  @Override
  public String getAttribute(String name) {
    return element.getAttribute(name);
  }

  @Override
  public boolean isSelected() {
    return element.isSelected();
  }

  @Override
  public boolean isEnabled() {
    return element.isEnabled();
  }

  @Override
  public String getText() {
    return element.getText();
  }

  @Override
  public List<WebElement> findElements(By by) {
    return element.findElements(by);
  }

  @Override
  public WebElement findElement(By by) {
    return element.findElement(by);
  }

  @Override
  public boolean isDisplayed() {
    return element.isDisplayed();
  }

  @Override
  public Point getLocation() {
    return element.getLocation();
  }

  @Override
  public Dimension getSize() {
    return element.getSize();
  }

  @Override
  public Rectangle getRect() {
    return element.getRect();
  }

  @Override
  public String getCssValue(String propertyName) {
    return element.getCssValue(propertyName);
  }

  @Override
  public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
    return element.getScreenshotAs(target);
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class GetElementBy {

    public static UiElement name(String byName) {
      return new UiElement(By.name(byName));
    }

    public static UiElement id(String byId) {
      return new UiElement(By.id(byId));
    }

    public static UiElement className(String byClassName) {
      return new UiElement(By.className(byClassName));
    }

    public static UiElement text(String byText) {
      return new UiElement(By.name(byText));
    }

    public static UiElement xpath(String xpathLocator) {
      return new UiElement(By.xpath(xpathLocator));
    }
  }
}
