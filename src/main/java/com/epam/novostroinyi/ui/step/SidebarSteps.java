package com.epam.novostroinyi.ui.step;

import com.epam.novostroinyi.ui.page.Sidebar;

public class SidebarSteps extends BaseUiSteps<Sidebar> {

  public SidebarSteps() {
    super(new Sidebar());
  }

  public LaunchesSteps openLaunches() {
    getReporter().step("Switching to Launches page");
    getPage().getLaunchesTab().click();
    return new LaunchesSteps();
  }
}
