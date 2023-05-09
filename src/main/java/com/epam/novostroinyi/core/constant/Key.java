package com.epam.novostroinyi.core.constant;

import org.openqa.selenium.Keys;

public enum Key {

  ENTER(Keys.ENTER);

  private Keys key;

  Key(Keys key){
    this.key = key;
  }

  public Keys getKey() {
    return key;
  }
}
