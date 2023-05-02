package com.epam.novostroinyi.page;

import com.codeborne.selenide.WebDriverRunner;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

  protected void waitForPageLoading(By loadableElement) {
    WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(),
        Duration.of(10, ChronoUnit.SECONDS));
    wait.until(ExpectedConditions.visibilityOfElementLocated(loadableElement));
  }
}
