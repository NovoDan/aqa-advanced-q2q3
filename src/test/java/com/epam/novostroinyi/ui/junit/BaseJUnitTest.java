package com.epam.novostroinyi.ui.junit;

import static com.epam.novostroinyi.core.constant.LaunchesConstants.LAUNCH_EXPORT_DIR_PATH;
import static com.epam.novostroinyi.core.util.WebDriverUtils.openUrl;
import static com.epam.novostroinyi.core.util.WebDriverUtils.setBrowser;

import com.codeborne.selenide.Configuration;
import com.epam.novostroinyi.core.config.Property;
import com.epam.novostroinyi.core.constant.Browser;
import com.epam.novostroinyi.core.util.WebDriverUtils;
import com.epam.novostroinyi.ui.IBaseUiTest;
import com.epam.novostroinyi.ui.step.LoginPageSteps;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;

@RunWith(TestRunner.class)
public class BaseJUnitTest implements IBaseUiTest {

  @BeforeAll
  @SneakyThrows
  public static void setUp() {
    Configuration.downloadsFolder = LAUNCH_EXPORT_DIR_PATH;
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
  public void tearDown() {
    WebDriverUtils.closeWebDriver();
  }
}
