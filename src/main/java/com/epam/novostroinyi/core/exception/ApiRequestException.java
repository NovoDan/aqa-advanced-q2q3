package com.epam.novostroinyi.core.exception;

public class ApiRequestException extends RuntimeException {

  public ApiRequestException(String message, Throwable exception) {
    super(message, exception);
  }

  public ApiRequestException(String message) {
    super(message);
  }

}
