package com.epam.novostroinyi.core.ui.element;

import static com.epam.novostroinyi.core.util.WebDriverUtils.executeJs;
import static com.epam.novostroinyi.core.util.WebDriverUtils.executeJsAndReturnResult;

import com.epam.novostroinyi.core.config.ConfigUtils;
import com.epam.novostroinyi.core.logger.ILogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WebActions extends Actions {

  private ILogger logger = ConfigUtils.getLogger();

  public WebActions(WebDriver driver) {
    super(driver);
  }

  @Override
  public Actions click(WebElement target) {
    logger.debug("Clicking an element " + target.toString());
    executeJs("arguments[0].click()", target);
    return this;
  }

  @Override
  public Actions dragAndDrop(WebElement source, WebElement target) {
    logger.debug("Moving element {} to {}", source, target);
    return super.dragAndDrop(source, target);
  }

  public boolean isScrolledIntoView(WebElement target) {
    logger.debug("Verifying that element {} is visible after scrolling", target);
    return Boolean.TRUE.equals(executeJsAndReturnResult("""
    var rect = arguments[0].getBoundingClientRect();
    var elemTop = rect.top;
    var elemBottom = rect.bottom;
    var isVisible = (elemTop >= 0) && (elemBottom <= window.innerHeight);
    return isVisible;
        """, target, Boolean.class));
  }

  @Override
  public Actions scrollToElement(WebElement element) {
    logger.debug("Scrolling to element " + element.toString());
    executeJs("arguments[0].scrollIntoView();", element);
    return this;
  }
}
