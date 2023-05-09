package com.epam.novostroinyi.step;

import static com.epam.novostroinyi.core.constant.Key.ENTER;

import com.epam.novostroinyi.core.ui.element.UiElementsCollection;
import com.epam.novostroinyi.page.TestItemsPage;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;

public class TestItemsSteps extends BaseUiSteps<TestItemsPage> {

  public TestItemsSteps() {
    super(new TestItemsPage());
  }

  @SneakyThrows
  public TestItemsSteps applyFilter(String searchPhrase) {
    getReporter().step(String.format("Test items filtering by value '%s'", searchPhrase));
    getPage().getSearch().sendKeys(searchPhrase, ENTER.getKey());
    TimeUnit.SECONDS.sleep(2);
    getPage().getItems().refreshElement();
    return this;
  }

  public UiElementsCollection getTestItems() {
    return getPage().getItems();
  }
}
