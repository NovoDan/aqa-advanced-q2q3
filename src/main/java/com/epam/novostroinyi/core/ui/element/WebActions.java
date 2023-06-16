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
//    return super.dragAndDrop(source, target);

    executeJs("function createEvent(typeOfEvent) {\\n\" +\n"
        + "                \"    var event = document.createEvent(\\\"CustomEvent\\\");\\n\" +\n"
        + "                \"    event.initCustomEvent(typeOfEvent, true, true, null);\\n\" +\n"
        + "                \"    event.dataTransfer = {\\n\" +\n"
        + "                \"        data: {},\\n\" +\n"
        + "                \"        setData: function (key, value) {\\n\" +\n"
        + "                \"            this.data[key] = value;\\n\" +\n"
        + "                \"        },\\n\" +\n"
        + "                \"        getData: function (key) {\\n\" +\n"
        + "                \"            return this.data[key];\\n\" +\n"
        + "                \"        }\\n\" +\n"
        + "                \"    };\\n\" +\n"
        + "                \"    return event;\\n\" +\n"
        + "                \"}\\n\" +\n"
        + "                \"\\n\" +\n"
        + "                \"function dispatchEvent(element, event, transferData) {\\n\" +\n"
        + "                \"    if (transferData !== undefined) {\\n\" +\n"
        + "                \"        event.dataTransfer = transferData;\\n\" +\n"
        + "                \"    }\\n\" +\n"
        + "                \"    if (element.dispatchEvent) {\\n\" +\n"
        + "                \"        element.dispatchEvent(event);\\n\" +\n"
        + "                \"    } else if (element.fireEvent) {\\n\" +\n"
        + "                \"        element.fireEvent(\\\"on\\\" + event.type, event);\\n\" +\n"
        + "                \"    }\\n\" +\n"
        + "                \"}\\n\" +\n"
        + "                \"\\n\" +\n"
        + "                \"function simulateHTML5DragAndDrop(element, target) {\\n\" +\n"
        + "                \"    var dragStartEvent = createEvent('dragstart');\\n\" +\n"
        + "                \"    dispatchEvent(element, dragStartEvent);\\n\" +\n"
        + "                \"\\n\" +\n"
        + "                \"    var dropEvent = createEvent('drop');\\n\" +\n"
        + "                \"    dispatchEvent(target, dropEvent, dragStartEvent.dataTransfer);\\n\" +\n"
        + "                \"\\n\" +\n"
        + "                \"    var dragEndEvent = createEvent('dragend');\\n\" +\n"
        + "                \"    dispatchEvent(element, dragEndEvent, dropEvent.dataTransfer);\\n\" +\n"
        + "                \"}\\n\" +\n"
        + "                \"\\n\" +\n"
        + "                \"var sourceElement = arguments[0];\\n\" +\n"
        + "                \"var targetElement = arguments[1];\\n\" +\n"
        + "                \"simulateHTML5DragAndDrop(sourceElement, targetElement);", source, target);
    return this;
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
