package com.epam.novostroinyi.ui.step;

import static com.codeborne.selenide.Selenide.actions;
import static com.epam.novostroinyi.core.constant.TestStatus.FAILED;
import static com.epam.novostroinyi.core.constant.TestStatus.PASSED;
import static com.epam.novostroinyi.core.constant.TestStatus.PRODUCT_BUGS;
import static com.epam.novostroinyi.core.constant.TestStatus.SKIPPED;
import static com.epam.novostroinyi.core.constant.TestStatus.SYSTEM_ISSUE;
import static com.epam.novostroinyi.core.constant.TestStatus.TA_BUGS;
import static com.epam.novostroinyi.core.constant.TestStatus.TOTAL;
import static com.epam.novostroinyi.core.constant.TestStatus.TO_INVESTIGATE;

import com.epam.novostroinyi.core.ui.element.UiElementsCollection;
import com.epam.novostroinyi.ui.page.LaunchesPage;
import java.util.Map;
import org.openqa.selenium.WebElement;

public class LaunchesSteps extends BaseUiSteps<LaunchesPage> {

  public LaunchesSteps() {
    super(new LaunchesPage());
  }

  public UiElementsCollection getLaunchesList() {
    return getPage().getLaunchesList();
  }

  public TestItemsSteps openLaunch(int id) {
    getReporter().step("Opening launch with id: " + id);
    UiElementsCollection launches = getLaunchesList();
    WebElement launchToOpen = launches.get(launches.size() - id);

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

    return Map.of(TOTAL.getStatus(), launchTotalTests,
        PASSED.getStatus(), launchPassedTests,
        SKIPPED.getStatus(), launchSkippedTests,
        FAILED.getStatus(), launchFailedTests,
        PRODUCT_BUGS.getStatus(), launchProductBugs,
        TA_BUGS.getStatus(), launchAutomationBugs,
        SYSTEM_ISSUE.getStatus(), launchSystemIssues,
        TO_INVESTIGATE.getStatus(), launchToInvestigateBugs);
  }
}
