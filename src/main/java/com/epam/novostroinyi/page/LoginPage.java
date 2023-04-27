package com.epam.novostroinyi.page;

import com.epam.novostroinyi.core.ui.element.UiElement;
import com.epam.novostroinyi.core.ui.element.UiElement.GetElementBy;
import lombok.Getter;

@Getter
public class LoginPage implements BasePage {

  private final UiElement loginField = GetElementBy.name("login");
  private final UiElement passwordField = GetElementBy.name("password");
  private final UiElement submitButton = GetElementBy.xpath("//button[@type='submit']");
}
