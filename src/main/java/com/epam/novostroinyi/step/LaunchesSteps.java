package com.epam.novostroinyi.step;

import static com.codeborne.selenide.Selenide.actions;

import com.epam.novostroinyi.core.ui.element.UiElementsCollection;
import com.epam.novostroinyi.page.LaunchesPage;

public class LaunchesSteps extends BaseUiSteps<LaunchesPage> {

  public LaunchesSteps() {
    super(new LaunchesPage());
  }

  public UiElementsCollection getLaunchesList() {
    return getPage().getLaunchesList();
  }

  public TestItemsSteps openLaunch(int id) {
    var launches = getLaunchesList();
    var launchToOpen = launches.get(launches.size() - id);

    actions().scrollToElement(launchToOpen).perform();
    launchToOpen.click();

    return new TestItemsSteps();
  }

  public LaunchesSteps switchSortingItemsByName() {
    actions().moveToElement(getPage().getLaunchesTableHeader().get(1)).click().perform();
    return this;
  }
}
