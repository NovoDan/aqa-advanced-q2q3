package com.epam.novostroinyi.ui;

import static com.epam.novostroinyi.core.util.WebDriverUtils.setBrowser;

public interface IBaseUiTest {

  static void setUp() {
    setBrowser();
  }

  void login();

   void tearDown();
}
