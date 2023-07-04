package com.epam.novostroinyi.ui.step;

import com.epam.novostroinyi.ui.page.BasePage;
import lombok.Getter;

@Getter
public class BaseUiSteps<PAGE extends BasePage> extends BaseSteps {

  private final PAGE page;

  public BaseUiSteps(PAGE page) {
    this.page = page;
  }
}
