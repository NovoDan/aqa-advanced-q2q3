package com.epam.novostroinyi.ui.junit;

import com.epam.novostroinyi.ui.junit.listener.UiTestListener;
import org.junit.AssumptionViolatedException;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

public class TestRunner extends BlockJUnit4ClassRunner {

  public TestRunner(Class<?> testClass) throws InitializationError {
    super(testClass);
  }

  @Override
  public void run(RunNotifier notifier) {
    notifier.addListener(new UiTestListener());
    EachTestNotifier testNotifier = new EachTestNotifier(notifier,
        getDescription());
    try {
      notifier.fireTestRunStarted(getDescription());
      Statement statement = classBlock(notifier);
      statement.evaluate();
    }
    catch (AssumptionViolatedException e) {
      testNotifier.fireTestIgnored();
    }
    catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }
}
