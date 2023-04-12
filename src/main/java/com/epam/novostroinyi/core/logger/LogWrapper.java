package com.epam.novostroinyi.core.logger;

public interface LogWrapper {

  void info(String message);

  void info(String message, Object... objects);

  void debug(String message);

  void debug(String message, Object... objects);

  void error(String message);

  void error(String message, Object... objects);
}
