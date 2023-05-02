package com.epam.novostroinyi.step;

import static com.codeborne.selenide.Selenide.actions;

import com.epam.novostroinyi.core.ui.element.UiElementsCollection;
import com.epam.novostroinyi.page.LaunchesPage;
import java.util.Map;

public class LaunchesSteps extends BaseUiSteps<LaunchesPage> {

  public LaunchesSteps() {
    super(new LaunchesPage());
  }

  public UiElementsCollection getLaunchesList() {
    return getPage().getLaunchesList();
  }

  public TestItemsSteps openLaunch(int id) {
    getReporter().step("Opening launch with id: " + id);
    var launches = getLaunchesList();
    var launchToOpen = launches.get(launches.size() - id);

    actions().scrollToElement(launchToOpen).perform();
    launchToOpen.click();

    return new TestItemsSteps();
  }

  public Map<String, String> getLaunchStatistics(int launchId) {
    getReporter().step("Getting statistics for launch with id " + launchId);
    int launchIndex = getLaunchesList().size() - launchId;
    String launchTotalTests = getPage().getLaunchesTotalColumn().get(launchIndex).getText();
    String launchPassedTests = getPage().getLaunchesPassedColumn().get(launchIndex).getText();
    String launchSkippedTests =
        getPage().getLaunchesSkippedColumn().get(launchIndex).getText();
    String launchFailedTests =
        getPage().getLaunchesFailedColumn().get(launchIndex).getText();
    String launchProductBugs = getPage().getProductBugColumn().get(launchIndex).getText();
    String launchAutomationBugs = getPage().getProductAutoBugColumn().get(launchIndex).getText();
    String launchSystemIssues = getPage().getProductSystemIssueColumn().get(launchIndex).getText();
    String launchToInvestigateBugs = getPage().getProductToInvestigateColumn().get(launchIndex)
        .getText();

    return Map.of("totalTests", launchTotalTests,
        "passed", launchPassedTests,
        "skipped", launchSkippedTests,
        "failed", launchFailedTests,
        "productBugs", launchProductBugs,
        "automationBugs", launchAutomationBugs,
        "systemIssues", launchSystemIssues,
        "toInvestigate", launchToInvestigateBugs);
  }
}
