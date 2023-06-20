package com.epam.novostroinyi.ui.step;

import com.epam.novostroinyi.ui.page.BasePage;
import lombok.Getter;

@Getter
public class BaseUiSteps<PAGE extends BasePage> extends BaseSteps {

  private PAGE page;

  public BaseUiSteps(PAGE page) {
    this.page = page;
  }

  public void reloadPageElements(PAGE page) {
    this.page = page;
  }
}
