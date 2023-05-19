package com.epam.novostroinyi.ui.junit;

import static com.epam.novostroinyi.core.config.ConfigUtils.setBrowser;
import static com.epam.novostroinyi.core.util.WebDriverUtils.openUrl;

import com.epam.novostroinyi.core.config.Property;
import com.epam.novostroinyi.core.util.WebDriverUtils;
import com.epam.novostroinyi.ui.step.LoginPageSteps;
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
    openUrl(Property.COMMON_PROPERTY.baseUrl());
    new LoginPageSteps().logIntoReportPortal().openLaunches();
  }

  @AfterEach
  public void tearDown() {
    WebDriverUtils.closeWebDriver();
  }

}
