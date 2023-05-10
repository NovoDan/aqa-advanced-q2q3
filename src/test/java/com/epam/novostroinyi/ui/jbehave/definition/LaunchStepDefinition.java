package com.epam.novostroinyi.ui.jbehave.definition;

import static com.epam.novostroinyi.core.assertion.Assert.assertHasSize;
import static com.epam.novostroinyi.core.util.WebDriverUtils.openUrl;

import com.epam.novostroinyi.core.config.Property;
import com.epam.novostroinyi.core.ui.element.UiElementsCollection;
import com.epam.novostroinyi.core.util.WebDriverUtils;
import com.epam.novostroinyi.step.LaunchesSteps;
import com.epam.novostroinyi.step.LoginPageSteps;
import com.epam.novostroinyi.step.TestItemsSteps;
import lombok.SneakyThrows;
import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class LaunchStepDefinition {

  private LaunchesSteps launchesSteps;

  @BeforeScenario
  public void beforeScenario() {
    openUrl(Property.COMMON_PROPERTY.baseUrl());
  }

  @AfterScenario
  public void tearDown() {
    WebDriverUtils.closeWebDriver();
  }

  @Given("Launch Page is opened")
  public void openLaunchPage() {
    launchesSteps = new LoginPageSteps().logIntoReportPortal().openLaunches();
  }

  @SneakyThrows
  @Given("Launch with id $launchId is opened")
  public void openLaunch(int launchId) {
    UiElementsCollection launchesList = launchesSteps.getLaunchesList();
    while(launchesList.size() == 0) {
      launchesList.refreshElement();
    }
    launchesList.scrollTo(launchId);
    launchesSteps.openLaunch(launchId);
  }

  @When("Search phrase '$searchPhrase' is entered")
  public void filterTestItems(String searchPhrase) {
    new TestItemsSteps().applyFilter(searchPhrase);
  }

  @Then("Test Items number after filter is $itemsSize")
  public void checkThatItemsAreFiltered(int itemsSize) {
    assertHasSize(new TestItemsSteps().getTestItems(), itemsSize, "Expected Items list size is not equal to expected");
  }
}
