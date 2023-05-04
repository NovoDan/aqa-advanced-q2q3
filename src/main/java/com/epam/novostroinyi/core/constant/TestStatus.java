package com.epam.novostroinyi.core.constant;

public enum TestStatus {

  TOTAL("totalTests"),
  PASSED("passed"),
  FAILED("failed"),
  SKIPPED("skipped"),
  PRODUCT_BUGS("productBugs"),
  TA_BUGS("automationBugs"),
  SYSTEM_ISSUE("systemIssues"),
  TO_INVESTIGATE("toInvestigate");

  private String status;

  TestStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}
