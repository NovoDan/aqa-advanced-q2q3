package com.epam.novostroinyi.core.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jLogger implements LogWrapper {

  private final Logger logger;

  public Log4jLogger() {
    this.logger = LogManager.getLogger();
  }

  @Override
  public void info(String message) {
    logger.info(message);
  }

  @Override
  public void info(String message, Object... objects) {
    logger.info(message, objects);
  }

  @Override
  public void debug(String message) {
    logger.debug(message);
  }

  @Override
  public void debug(String message, Object... objects) {
    logger.debug(message, objects);
  }

  @Override
  public void error(String message) {
    logger.error(message);
  }

  @Override
  public void error(String message, Object... objects) {
    logger.error(message, objects);
  }
}
