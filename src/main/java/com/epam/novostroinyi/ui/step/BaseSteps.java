package com.epam.novostroinyi.ui.step;

import com.epam.novostroinyi.core.config.ConfigUtils;
import com.epam.novostroinyi.core.logger.ILogger;
import com.epam.novostroinyi.core.reporter.Reporter;
import lombok.Getter;

@Getter
public abstract class BaseSteps {

  private final ILogger logger = ConfigUtils.getLogger();
  private final Reporter reporter = ConfigUtils.getReporter();
}
