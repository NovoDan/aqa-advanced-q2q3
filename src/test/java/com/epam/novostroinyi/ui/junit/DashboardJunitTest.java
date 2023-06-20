package com.epam.novostroinyi.ui.junit;

import com.epam.novostroinyi.ui.step.SidebarSteps;
import org.junit.jupiter.api.Test;

public class DashboardJunitTest extends BaseJUnitTest {

  @Test
  public void changeWidgetsOrderTest() {
    int targetWidget = 0;
    int destinationWidget = 2;

    new SidebarSteps().openDasboards().openDashboard(0)
        .changeWidgetsOrder(targetWidget, destinationWidget);

    System.out.println("");
  }
}
