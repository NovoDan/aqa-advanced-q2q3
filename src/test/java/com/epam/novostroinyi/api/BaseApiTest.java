package com.epam.novostroinyi.api;

import com.epam.novostroinyi.core.annotation.CaseId;
import com.epam.novostroinyi.core.constant.StatusId;
import com.epam.novostroinyi.core.notification.NotifierDecorator;
import com.epam.novostroinyi.core.notification.SlackDecorator;
import com.epam.novostroinyi.core.notification.TestRailDecorator;
import com.epam.novostroinyi.core.notification.TestRailNotifier;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class BaseApiTest {

  @AfterMethod
  public static void sendResults(Method method, ITestResult result) {
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

    new NotifierDecorator(
        new SlackDecorator(new TestRailDecorator(new TestRailNotifier()))).sendMessage(testResults);
  }

}
