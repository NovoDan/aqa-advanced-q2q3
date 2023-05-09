package com.epam.novostroinyi.ui.jbehave.definition;

import static com.epam.novostroinyi.core.util.WebDriverUtils.executeJs;
import static com.epam.novostroinyi.core.util.WebDriverUtils.openUrl;
import static org.assertj.core.api.Assertions.assertThat;

import com.epam.novostroinyi.core.config.Property;
import com.epam.novostroinyi.core.util.WebDriverUtils;
import com.epam.novostroinyi.step.LaunchesSteps;
import com.epam.novostroinyi.step.LoginPageSteps;
import com.epam.novostroinyi.step.SidebarSteps;
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
    while(launchesSteps.getLaunchesList().size() == 0) {
      launchesSteps.getLaunchesList().refreshElement();
      Thread.sleep(1000);
    }
    executeJs("arguments[0].scrollIntoView(true);", launchesSteps.getLaunchesList().get(launchId));
    launchesSteps.getLaunchesList().get(launchId).click();
  }

  @When("Search phrase '$searchPhrase' is entered")
  public void filterTestItems(String searchPhrase) {
    new TestItemsSteps().applyFilter(searchPhrase);
  }

  @Then("Test Items number after filter is $itemsSize")
  public void checkThatItemsAreFiltered(int itemsSize) {
    assertThat(new TestItemsSteps().getTestItems())
        .as("Expected Items list size is not equal to expected")
        .hasSize(itemsSize);
  }
}
