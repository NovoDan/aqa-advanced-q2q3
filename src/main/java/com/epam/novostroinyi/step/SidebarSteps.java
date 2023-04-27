package com.epam.novostroinyi.step;

import com.epam.novostroinyi.page.Sidebar;

public class SidebarSteps extends BaseUiSteps<Sidebar> {

  public SidebarSteps() {
    super(new Sidebar());
  }

  public LaunchesSteps openLaunches() {
    getPage().getLaunches().click();
    return new LaunchesSteps();
  }
}
