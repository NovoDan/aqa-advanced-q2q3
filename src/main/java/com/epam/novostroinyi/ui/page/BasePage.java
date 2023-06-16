package com.epam.novostroinyi.ui.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.epam.novostroinyi.core.util.WebDriverUtils;
import com.epam.novostroinyi.core.waiter.DriverFluentWaiter;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class BasePage {

  protected void waitForPageLoading(By loadableElement) {
//    DriverFluentWaiter wait = new DriverFluentWaiter(WebDriverUtils.getWebDriver(),
//        Duration.of(30, ChronoUnit.SECONDS));
//    wait.until(ExpectedConditions.visibilityOfElementLocated(loadableElement));
    $(loadableElement).shouldBe(Condition.visible);
  }
}
