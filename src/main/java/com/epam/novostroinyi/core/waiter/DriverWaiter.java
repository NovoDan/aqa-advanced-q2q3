package com.epam.novostroinyi.core.waiter;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverWaiter extends WebDriverWait {

  public DriverWaiter(WebDriver driver, Duration timeout) {
    super(driver, timeout);
  }
}
