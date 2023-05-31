package com.epam.novostroinyi.ui.jbehave.definition;

import static com.epam.novostroinyi.core.util.WebDriverUtils.openUrl;

import com.epam.novostroinyi.core.config.Property;
import com.epam.novostroinyi.core.util.WebDriverUtils;
import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;

public class BaseBddStepDefinition {

  @BeforeScenario
  public void beforeScenario() {
    openUrl(Property.COMMON_PROPERTY.baseUrl());
  }

  @AfterScenario
  public void tearDown() {
    WebDriverUtils.closeWebDriver();
  }

}
