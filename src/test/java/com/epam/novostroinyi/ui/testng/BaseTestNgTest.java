package com.epam.novostroinyi.ui.testng;

import static com.codeborne.selenide.Selenide.open;
import static com.epam.novostroinyi.core.config.ConfigUtils.setBrowser;

import com.codeborne.selenide.WebDriverRunner;
import com.epam.novostroinyi.core.config.Property;
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
    open(Property.COMMON_PROPERTY.baseUrl());
    new LoginPageSteps().logIntoReportPortal().openLaunches();
  }

  @AfterMethod
  public void tearDown() {
    WebDriverRunner.closeWebDriver();
  }


}
