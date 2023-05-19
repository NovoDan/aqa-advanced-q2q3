package com.epam.novostroinyi.ui.page;

import com.epam.novostroinyi.core.ui.element.UiElement;
import com.epam.novostroinyi.core.ui.element.UiElement.GetElementBy;
import lombok.Getter;

@Getter
public class Sidebar extends BasePage {

  private final UiElement launchesTab =
      GetElementBy.xpath(
          "//div[contains(@class, 'sidebar-nav-btn')]/a[contains(@href, 'launches')]");

  public Sidebar() {
    waitForPageLoading(launchesTab.getLocator());
  }
}
