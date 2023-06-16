package com.epam.novostroinyi.ui.step;

import com.epam.novostroinyi.ui.page.DashboardsPage;

public class DashboardsSteps extends BaseUiSteps<DashboardsPage> {

  public DashboardSteps openDashboard(int dashboardNumber) {
    getReporter().step("Opening dashboard with number '%d'".formatted(dashboardNumber));
    getPage().getDashboardTable().get(dashboardNumber).click();
    return new DashboardSteps();
  }

  public DashboardsSteps() {
    super(new DashboardsPage());
  }
}
