package com.epam.novostroinyi.core.exception;

public class CreateInstanceException extends RuntimeException {

  public CreateInstanceException(String message, Throwable exception) {
    super(message, exception);
  }

  public CreateInstanceException(String message) {
    super(message);
  }
}
