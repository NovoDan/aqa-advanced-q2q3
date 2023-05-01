package com.epam.novostroinyi.step;

import static io.qameta.allure.Allure.step;

import com.epam.novostroinyi.core.ui.element.UiElementsCollection;
import com.epam.novostroinyi.page.TestItemsPage;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import org.openqa.selenium.Keys;

public class TestItemsSteps extends BaseUiSteps<TestItemsPage> {

  public TestItemsSteps() {
    super(new TestItemsPage());
  }

  @SneakyThrows
  public TestItemsSteps applyFilter(String searchPhrase) {
    step(String.format("Test items filtering by value '%s'", searchPhrase));
    getPage().getSearch().sendKeys(searchPhrase, Keys.ENTER);
    getPage().getSearch().sendKeys(Keys.ENTER);
    TimeUnit.SECONDS.sleep(2);
    getPage().getItems().refreshElement();
    return this;
  }

  public UiElementsCollection getTestItems() {
    return getPage().getItems();
  }
}
