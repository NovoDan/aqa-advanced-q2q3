package com.epam.novostroinyi.core.util;

import static com.codeborne.selenide.FileDownloadMode.FOLDER;
import static com.codeborne.selenide.Selenide.open;
import static com.epam.novostroinyi.core.constant.LaunchesConstants.LAUNCH_EXPORT_DIR_PATH;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import com.epam.novostroinyi.core.config.Property;
import com.epam.novostroinyi.core.constant.Browser;
import com.epam.novostroinyi.core.exception.FileProcessingException;
import com.epam.novostroinyi.core.remote.RemoteClient;
import com.epam.novostroinyi.core.remote.SauceClient;
import com.epam.novostroinyi.core.waiter.ConditionalWaiter;
import com.epam.novostroinyi.core.waiter.SelenideWaiter;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WebDriverUtils {

  @Getter
  private static WebDriver webDriver;

  @Getter
  private static final ConditionalWaiter waiter = new SelenideWaiter();

  @Getter
  private static RemoteClient remoteClient;

  public static void openUrl(String url) {
    open(url);
    webDriver = WebDriverRunner.getWebDriver();
    getWebDriver().manage().window().maximize();
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
    } catch (ClassCastException e) {
      return null;
    }
  }

  public static void executeJs(String script, WebElement source, WebElement target) {
    JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
    executor.executeScript(script, source, target);
  }

  public static byte[] takeScreenshot() throws IOException {
    File screenshot = Screenshots.takeScreenShotAsFile();
    if (Objects.isNull(screenshot)) {
      throw new FileProcessingException("Screenshot was not taken");
    }
    return Files.toByteArray(screenshot);
  }

  public static void setBrowser() {
    configureSelenideBrowser();
    switch (Property.COMMON_PROPERTY.browserType()) {
      case "firefox" -> Configuration.browser = Browsers.FIREFOX;
      case "edge" -> Configuration.browser = Browsers.EDGE;
      default -> Configuration.browser = Browsers.CHROME;
    }
  }

  public static void configureRemoteBrowser(Browser browser) {
    configureSelenideBrowser();
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setBrowserName(browser.getBrowserName());
    capabilities.setPlatform(Platform.WIN10);

    String remoteClientUser = Property.COMMON_PROPERTY.remoteClientUsername();
    String remoteClientPassword = Property.SECRET_PROPERTY.remoteClientKey();
    remoteClient = new SauceClient(remoteClientUser, remoteClientPassword);
    Configuration.remote = Property.COMMON_PROPERTY.remoteBrowserUrl().formatted(remoteClientUser, remoteClientPassword);
  }

  private static void configureSelenideBrowser() {
    Configuration.proxyEnabled = false;
    Configuration.fileDownload = FOLDER;
    Configuration.downloadsFolder = LAUNCH_EXPORT_DIR_PATH;
  }

  public static String getSessionId() {
    return ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString();
  }
}
