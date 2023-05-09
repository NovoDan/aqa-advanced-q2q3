package com.epam.novostroinyi.ui.testng;

import static com.epam.novostroinyi.core.config.ConfigUtils.setBrowser;
import static com.epam.novostroinyi.core.util.WebDriverUtils.openUrl;

import com.epam.novostroinyi.core.config.Property;
import com.epam.novostroinyi.core.util.WebDriverUtils;
import com.epam.novostroinyi.step.LoginPageSteps;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTestNgTest {

  @BeforeClass
  public static void setUp() {
    setBrowser();
  }

  @BeforeMethod
  public void login() {
    openUrl(Property.COMMON_PROPERTY.baseUrl());
    new LoginPageSteps().logIntoReportPortal().openLaunches();
  }

  @AfterMethod
  public void tearDown() {
    WebDriverUtils.closeWebDriver();
  }


}
