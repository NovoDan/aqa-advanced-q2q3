package com.epam.novostroinyi.core.config;

import com.epam.novostroinyi.core.logger.Log4jLogger;
import com.epam.novostroinyi.core.logger.LogWrapper;
import com.epam.novostroinyi.core.reporter.AllureReporter;
import com.epam.novostroinyi.core.reporter.Reporter;
import lombok.Getter;

public class ConfigUtils {

  private static LogWrapper logger;
  private static Reporter reporter;

  static  {
    logger = new Log4jLogger();
    reporter = AllureReporter.getInstance();
  }

  public static LogWrapper getLogger() {
    return  logger;
  }

  public static Reporter getReporter() {
    return reporter;
  }
}
