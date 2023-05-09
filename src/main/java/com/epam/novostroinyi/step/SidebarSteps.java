package com.epam.novostroinyi.step;

import com.epam.novostroinyi.page.Sidebar;

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
