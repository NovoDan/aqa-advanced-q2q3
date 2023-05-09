package com.epam.novostroinyi.core.util;

import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebDriverUtils {

  public static void openUrl(String url) {
    open(url);
    getWebDriver().manage().window().maximize();
  }

  public static WebDriver getWebDriver() {
    return WebDriverRunner.getWebDriver();
  }

  public static void closeWebDriver() {
    WebDriverRunner.closeWebDriver();
  }

  public static void executeJs(String script, WebElement element) {
    JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
    executor.executeScript(script, element);
  }
}
