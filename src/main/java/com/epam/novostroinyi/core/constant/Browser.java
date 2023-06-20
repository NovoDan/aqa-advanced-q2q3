package com.epam.novostroinyi.core.constant;

import lombok.Getter;

public enum Browser {
  CHROME("chrome"),
  OPERA("opera"),
  FIREFOX("firefox"),
  EDGE("MicrosoftEdge");

  @Getter
  String browserName;

  private Browser(String browserName) {
    this.browserName = browserName;
  }
}
