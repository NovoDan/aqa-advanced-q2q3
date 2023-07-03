package com.epam.novostroinyi.core.remote;

import com.saucelabs.saucerest.SauceREST;

public class SauceClient implements RemoteClient {

  private SauceREST client;

  public SauceClient(String username, String password) {
    this.client = new SauceREST(username, password);
  }

  @Override
  public void testFailed(String sessionId) {
    client.jobFailed(sessionId);
  }

  @Override
  public void testPassed(String sessionId) {
    client.jobPassed(sessionId);
  }
}
