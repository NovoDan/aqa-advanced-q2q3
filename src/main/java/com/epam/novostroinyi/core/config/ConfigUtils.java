package com.epam.novostroinyi.core.config;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.epam.novostroinyi.core.client.api.ApiClient;
import com.epam.novostroinyi.core.client.api.RestClient;
import com.epam.novostroinyi.core.logger.Log4jLogger;
import com.epam.novostroinyi.core.logger.ILogger;
import com.epam.novostroinyi.core.reporter.AllureReporter;
import com.epam.novostroinyi.core.reporter.Reporter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfigUtils {

  @Getter
  private static Reporter reporter  = AllureReporter.getInstance();

  @Getter
  private static ILogger logger = new Log4jLogger(reporter);

  @Getter
  private static ApiClient client = new RestClient();

  public static void setBrowser() {
    switch (Property.COMMON_PROPERTY.browserType()) {
      case "firefox" -> Configuration.browser = Browsers.FIREFOX;
      case "edge" -> Configuration.browser = Browsers.EDGE;
      default -> Configuration.browser = Browsers.CHROME;
    }
  }
}
