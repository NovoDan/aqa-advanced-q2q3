package com.epam.novostroinyi.core.config;

import static com.codeborne.selenide.FileDownloadMode.FOLDER;
import static com.epam.novostroinyi.core.constant.LaunchesConstants.LAUNCH_EXPORT_DIR_PATH;

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
    Configuration.proxyEnabled = false;
    Configuration.fileDownload = FOLDER;
    Configuration.downloadsFolder = LAUNCH_EXPORT_DIR_PATH;
    switch (Property.COMMON_PROPERTY.browserType()) {
      case "firefox" -> Configuration.browser = Browsers.FIREFOX;
      case "edge" -> Configuration.browser = Browsers.EDGE;
      default -> Configuration.browser = Browsers.CHROME;
    }
  }
}
