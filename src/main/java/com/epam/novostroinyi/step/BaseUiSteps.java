package com.epam.novostroinyi.step;

import com.epam.novostroinyi.page.BasePage;
import lombok.Getter;

@Getter
public class BaseUiSteps<PAGE extends BasePage> extends BaseSteps {

  private final PAGE page;

  public BaseUiSteps(PAGE page) {
    this.page = page;
  }
}
