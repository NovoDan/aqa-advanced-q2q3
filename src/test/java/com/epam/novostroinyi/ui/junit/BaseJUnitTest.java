package com.epam.novostroinyi.ui.junit;

import static com.codeborne.selenide.Selenide.open;
import static com.epam.novostroinyi.core.config.ConfigUtils.setBrowser;

import com.codeborne.selenide.WebDriverRunner;
import com.epam.novostroinyi.core.config.Property;
import com.epam.novostroinyi.step.LoginPageSteps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseJUnitTest {

  @BeforeAll
  public static void setUp() {
    setBrowser();
  }

  @BeforeEach
  public void login() {
    open(Property.COMMON_PROPERTY.baseUrl());
    new LoginPageSteps().logIntoReportPortal().openLaunches();
  }

  @AfterEach
  public void tearDown() {
    WebDriverRunner.closeWebDriver();
  }

}
