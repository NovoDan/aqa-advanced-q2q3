package com.epam.novostroinyi.page;

import com.epam.novostroinyi.core.ui.element.UiElement;
import com.epam.novostroinyi.core.ui.element.UiElement.GetElementBy;
import lombok.Getter;

@Getter
public class Sidebar implements BasePage {

  private final UiElement launchesTab =
      GetElementBy.xpath(
          "//div[contains(@class, 'sidebar-nav-btn')]/a[contains(@href, 'launches')]");
}
