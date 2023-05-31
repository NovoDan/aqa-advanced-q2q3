package com.epam.novostroinyi.core.exception;

public class FileProcessingException extends RuntimeException {

  public FileProcessingException(String message, Throwable exception) {
    super(message, exception);
  }

  public FileProcessingException(String message) {
    super(message);
  }
}
