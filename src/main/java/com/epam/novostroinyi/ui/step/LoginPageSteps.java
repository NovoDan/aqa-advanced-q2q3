package com.epam.novostroinyi.ui.step;

import static com.epam.novostroinyi.core.config.Property.COMMON_PROPERTY;
import static com.epam.novostroinyi.core.config.Property.SECRET_PROPERTY;

import com.epam.novostroinyi.ui.page.LoginPage;

public class LoginPageSteps extends BaseUiSteps<LoginPage> {

  public LoginPageSteps() {
    super(new LoginPage());
  }

  public SidebarSteps logIntoReportPortal() {
    getReporter().step("Login to Report portal is started");
    getPage().getLoginField().sendKeys(COMMON_PROPERTY.reportPortalUser());
    getPage().getPasswordField().sendKeys(SECRET_PROPERTY.reportPortalPassword());
    getPage().getSubmitButton().click();
    return new SidebarSteps();
  }
}
