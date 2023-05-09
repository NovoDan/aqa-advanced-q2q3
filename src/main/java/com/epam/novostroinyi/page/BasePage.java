package com.epam.novostroinyi.page;

import com.epam.novostroinyi.core.util.WebDriverUtils;
import com.epam.novostroinyi.core.waiter.DriverWaiter;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class BasePage {

  protected void waitForPageLoading(By loadableElement) {
    DriverWaiter wait = new DriverWaiter(WebDriverUtils.getWebDriver(),
        Duration.of(10, ChronoUnit.SECONDS));
    wait.until(ExpectedConditions.visibilityOfElementLocated(loadableElement));
  }
}
