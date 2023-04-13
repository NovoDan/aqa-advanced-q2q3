package com.epam.novostroinyi.core.logger;

import com.epam.novostroinyi.core.reporter.AllureReporter;
import com.epam.novostroinyi.core.reporter.Reporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jLogger implements LogWrapper {

  private final Reporter reporter;

  private final Logger logger;

  public Log4jLogger() {
    this.logger = LogManager.getLogger();
    this.reporter = AllureReporter.getInstance();
  }

  @Override
  public void info(String message) {
    logger.info(message);
    reporter.reportLog(message);
  }

  @Override
  public void info(String message, Object... objects) {
    logger.info(message, objects);
  }

  @Override
  public void debug(String message) {
    logger.debug(message);
    reporter.reportLog(message);
  }

  @Override
  public void debug(String message, Object... objects) {
    logger.debug(message, objects);
  }

  @Override
  public void error(String message) {
    logger.error(message);
    reporter.reportLog(message);
  }

  @Override
  public void error(String message, Object... objects) {
    logger.error(message, objects);
  }
}
