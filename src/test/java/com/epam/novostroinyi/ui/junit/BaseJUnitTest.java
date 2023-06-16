package com.epam.novostroinyi.ui.junit;

import static com.epam.novostroinyi.core.config.ConfigUtils.setBrowser;
import static com.epam.novostroinyi.core.util.WebDriverUtils.openUrl;

import com.epam.novostroinyi.core.config.Property;
import com.epam.novostroinyi.core.constant.Browser;
import com.epam.novostroinyi.core.util.WebDriverUtils;
import com.epam.novostroinyi.ui.BaseUiSteps;
import com.epam.novostroinyi.ui.step.LoginPageSteps;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseJUnitTest implements BaseUiSteps {

  @BeforeAll
  @SneakyThrows
  public static void setUp() {
    if (Property.COMMON_PROPERTY.isRemote()) {
      Browser browser = Browser.valueOf(Property.COMMON_PROPERTY.browserType().toUpperCase());
      WebDriverUtils.configureRemoteBrowser(browser);
    } else {
      setBrowser();
    }
  }

  @BeforeEach
  @Override
  public void login() {
    openUrl(Property.COMMON_PROPERTY.baseUrl());
    new LoginPageSteps().logIntoReportPortal();
  }

  @AfterEach
  @Override
  public void tearDown() {
    WebDriverUtils.closeWebDriver();
  }

}
