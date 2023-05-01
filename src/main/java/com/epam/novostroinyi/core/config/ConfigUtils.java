package com.epam.novostroinyi.core.config;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.epam.novostroinyi.core.logger.Log4jLogger;
import com.epam.novostroinyi.core.logger.ILogger;
import com.epam.novostroinyi.core.reporter.AllureReporter;
import com.epam.novostroinyi.core.reporter.Reporter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfigUtils {

  private static ILogger logger;
  private static Reporter reporter;

  static  {
    reporter = AllureReporter.getInstance();
    logger = new Log4jLogger(reporter);
  }

  public static ILogger getLogger() {
    return  logger;
  }

  public static Reporter getReporter() {
    return reporter;
  }

  public static void setBrowser() {
    switch (Property.COMMON_PROPERTY.browserType()) {
      case "firefox" -> Configuration.browser = Browsers.FIREFOX;
      case "edge" -> Configuration.browser = Browsers.EDGE;
      default -> Configuration.browser = Browsers.CHROME;
    }
  }
}
