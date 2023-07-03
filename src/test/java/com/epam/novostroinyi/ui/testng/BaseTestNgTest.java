package com.epam.novostroinyi.ui.testng;

import static com.epam.novostroinyi.core.util.WebDriverUtils.openUrl;
import static com.epam.novostroinyi.core.util.WebDriverUtils.setBrowser;

import com.epam.novostroinyi.core.annotation.CaseId;
import com.epam.novostroinyi.core.config.Property;
import com.epam.novostroinyi.core.constant.Browser;
import com.epam.novostroinyi.core.constant.StatusId;
import com.epam.novostroinyi.core.notification.NotifierDecorator;
import com.epam.novostroinyi.core.notification.SlackDecorator;
import com.epam.novostroinyi.core.notification.SlackNotifier;
import com.epam.novostroinyi.core.notification.TestRailDecorator;
import com.epam.novostroinyi.core.util.WebDriverUtils;
import com.epam.novostroinyi.ui.step.LoginPageSteps;
import com.epam.novostroinyi.ui.testng.listener.UiTestNgListener;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({UiTestNgListener.class, ReportPortalTestNGListener.class})
public class BaseTestNgTest {

  @BeforeClass
  public static void setUp() {
    if (Property.COMMON_PROPERTY.isRemote()) {
      Browser browser = Browser.valueOf(Property.COMMON_PROPERTY.browserType().toUpperCase());
      WebDriverUtils.configureRemoteBrowser(browser);
    } else {
      setBrowser();
    }
  }

  @BeforeMethod
  public void login() {
    openUrl(Property.COMMON_PROPERTY.baseUrl());
    new LoginPageSteps().logIntoReportPortal();
  }

  @AfterMethod
  public void tearDown(Method method, ITestResult result) {
    String executedTestName = method.getName();
    Map<String, String> testResults = new HashMap<>();

    switch (result.getStatus()) {
      case ITestResult.SUCCESS -> {
        testResults.put("message", executedTestName + " Test succeeded");
        testResults.put("statusId", StatusId.PASSED);
      }
      case ITestResult.FAILURE -> {
        testResults.put("message", executedTestName + " Test succeeded");
        testResults.put("statusId", StatusId.FAILED);
      }
      case ITestResult.SKIP -> {
        testResults.put("message", executedTestName + " Test succeeded");
        testResults.put("statusId", StatusId.RETEST);
      }
    }

    if (method.isAnnotationPresent(CaseId.class)) {
      testResults.put("caseId", method.getAnnotation(CaseId.class).caseId());
    }

    NotifierDecorator notifier = new NotifierDecorator(new TestRailDecorator(new SlackDecorator(new SlackNotifier())));
    notifier.sendMessage(testResults);
    WebDriverUtils.closeWebDriver();
  }
}
