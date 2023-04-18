package com.epam.novostroinyi.step;

import com.epam.novostroinyi.core.config.ConfigUtils;
import com.epam.novostroinyi.core.logger.LogWrapper;
import com.epam.novostroinyi.core.reporter.Reporter;

public abstract class BaseSteps {

  private LogWrapper logger = ConfigUtils.getLogger();
  private Reporter reporter = ConfigUtils.getReporter();
}
