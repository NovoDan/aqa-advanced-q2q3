package com.epam.novostroinyi.ui.step;

import com.epam.novostroinyi.core.ui.element.WebActions;
import com.epam.novostroinyi.core.util.WebDriverUtils;
import com.epam.novostroinyi.ui.page.DashboardPage;

public class DashboardSteps extends BaseUiSteps<DashboardPage> {

  public DashboardSteps() {
    super(new DashboardPage());
  }

  public DashboardSteps changeWidgetsOrder(int targetDashboard, int destinationDashboard) {
    WebActions actions = new WebActions(WebDriverUtils.getWebDriver());
    actions.dragAndDrop(getPage().getDraggableWidgets().get(targetDashboard),
        getPage().getDraggableWidgets()
            .get(destinationDashboard));
    return this;
  }
}
