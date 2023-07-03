package com.epam.novostroinyi.core.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum StatusCode {

  OK(200),
  CREATED(201),
  BAD_REQUEST(400),
  NOT_FOUND(404);

  @Getter
  private int code;

}
