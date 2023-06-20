package com.epam.novostroinyi.ui.testng;

import static com.epam.novostroinyi.core.util.WebDriverUtils.openUrl;
import static com.epam.novostroinyi.core.util.WebDriverUtils.setBrowser;

import com.epam.novostroinyi.core.config.Property;
import com.epam.novostroinyi.core.constant.Browser;
import com.epam.novostroinyi.core.util.WebDriverUtils;
import com.epam.novostroinyi.ui.step.LoginPageSteps;
import com.epam.novostroinyi.ui.testng.listener.UiTestNgListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(UiTestNgListener.class)
public class BaseTestNgTest {

  @BeforeClass
  public static void setUp() {
    if (Property.COMMON_PROPERTY.isRemote()) {
      Browser browser = Browser.valueOf(Property.COMMON_PROPERTY.browserType().toUpperCase());
      WebDriverUtils.configureRemoteBrowser(browser);
    } else {
      setBrowser();
    }
  }

  @BeforeMethod
  public void login() {
    openUrl(Property.COMMON_PROPERTY.baseUrl());
    new LoginPageSteps().logIntoReportPortal();
  }

  @AfterMethod
  public void tearDown() {
    WebDriverUtils.closeWebDriver();
  }


}
