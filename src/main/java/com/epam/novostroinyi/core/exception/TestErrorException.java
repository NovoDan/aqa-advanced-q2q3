package com.epam.novostroinyi.core.exception;

public class TestErrorException extends RuntimeException {

  public TestErrorException(String message, Throwable exception) {
    super(message, exception);
  }

  public TestErrorException(String message) {
    super(message);
  }
}
