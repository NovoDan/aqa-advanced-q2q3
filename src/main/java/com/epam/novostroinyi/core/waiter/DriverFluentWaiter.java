package com.epam.novostroinyi.core.waiter;

import java.time.Duration;
import java.util.function.Function;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverFluentWaiter<T> extends WebDriverWait /*implements Waiter<T>*/ {

  public DriverFluentWaiter(WebDriver driver, Duration timeout) {
    super(driver, timeout);
  }

//  public <V> V until(Function<? super WebDriver, V> isTrue) {
//    return null;
//  }
}
