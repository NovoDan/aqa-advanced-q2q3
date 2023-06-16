package com.epam.novostroinyi.core.util;

import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import com.epam.novostroinyi.core.config.Property;
import com.epam.novostroinyi.core.constant.Browser;
import com.epam.novostroinyi.core.exception.FileProcessingException;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WebDriverUtils {

  private static WebDriver webDriver;

  public static void openUrl(String url) {
    open(url);
    getWebDriver().manage().window().maximize();
  }

  public static WebDriver getWebDriver() {
    if (Objects.isNull(webDriver)) {
      webDriver = WebDriverRunner.getWebDriver();
    }
    return webDriver;
  }

  public static void closeWebDriver() {
    WebDriverRunner.closeWebDriver();
  }

  public static void executeJs(String script, WebElement element) {
    JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
    executor.executeScript(script, element);
  }

  public static <T> T executeJsAndReturnResult(String script, WebElement element, Class<T> clazz) {
    JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
    Object result = executor.executeScript(script, element);
    try {
      return clazz.cast(result);
    } catch(ClassCastException e) {
      return null;
    }
  }

  public static void executeJs(String script, WebElement source, WebElement target) {
    JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
    executor.executeScript(script,source, target);
  }

  public static byte[] takeScreenshot() throws IOException {
    File screenshot = Screenshots.takeScreenShotAsFile();
    if (Objects.isNull(screenshot)) {
      throw new FileProcessingException("Screenshot was not taken");
    }
    return Files.toByteArray(screenshot);
  }

  public static void configureRemoteBrowser(Browser browser) throws MalformedURLException {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setBrowserName(browser.getBrowserName());
    capabilities.setPlatform(Platform.WIN10);
    webDriver = new RemoteWebDriver(new URL(Property.COMMON_PROPERTY.remoteBrowserUrl()), capabilities);
  }
}
