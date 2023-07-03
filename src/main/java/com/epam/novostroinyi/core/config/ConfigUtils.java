package com.epam.novostroinyi.core.config;

import com.epam.novostroinyi.core.client.api.ApiClient;
import com.epam.novostroinyi.core.client.api.RestClient;
import com.epam.novostroinyi.core.logger.ILogger;
import com.epam.novostroinyi.core.logger.Log4jLogger;
import com.epam.novostroinyi.core.reporter.AllureReporter;
import com.epam.novostroinyi.core.reporter.Reporter;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfigUtils {

  @Getter
  private static final Reporter reporter = AllureReporter.getInstance();

  @Getter
  private static final ILogger logger = new Log4jLogger(reporter);

  @Getter
  private static final ApiClient client = new RestClient(Map.of(
      "Authorization", "Bearer " + Property.SECRET_PROPERTY.reportPortalToken(),
      "Content-Type", "application/json",
      "Accept", "application/json"));

}
