package com.epam.novostroinyi.core.reporter;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;

public class AllureReporter extends BaseReporter implements Reporter {

  public static Reporter getInstance() {
    return getInstance(AllureReporter.class);
  }

  @Override
  public void addAttachment(String name, String content) {
    Allure.addAttachment(name, content);
  }

  @Override
  public void reportLog(String message) {
    addAttachment("[LOG]", message);
  }

  @Step("{0}")
  public void step(String message) {
    //Provided parameter will be added to report as 'Step' message
  }
}
