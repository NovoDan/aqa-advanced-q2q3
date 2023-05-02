package com.epam.novostroinyi.page;

import com.epam.novostroinyi.core.ui.element.UiElement;
import com.epam.novostroinyi.core.ui.element.UiElement.GetElementBy;
import lombok.Getter;

@Getter
public class LoginPage extends BasePage {

  private final UiElement loginField = GetElementBy.name("login");
  private final UiElement passwordField = GetElementBy.name("password");
  private final UiElement submitButton = GetElementBy.xpath("//button[@type='submit']");

  public LoginPage() {
    waitForPageLoading(loginField.getLocator());
  }
}
