package com.epam.novostroinyi.core.util;

import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;

public class WebDriverUtils {

  public static void openUrl(String url) {
    open(url);
  }

  public static WebDriver getWebDriver() {
    return WebDriverRunner.getWebDriver();
  }

  public static void closeWebDriver() {
    WebDriverRunner.closeWebDriver();
  }
}
