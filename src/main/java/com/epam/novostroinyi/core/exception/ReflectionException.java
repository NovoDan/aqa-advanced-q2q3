package com.epam.novostroinyi.core.exception;

public class ReflectionException extends RuntimeException {

  public ReflectionException(String message, Throwable exception) {
    super(message, exception);
  }

  public ReflectionException(String message) {
    super(message);
  }
}
