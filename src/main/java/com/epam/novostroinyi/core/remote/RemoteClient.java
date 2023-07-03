package com.epam.novostroinyi.core.remote;

public interface RemoteClient {

  void testFailed(String sessionId);
  void testPassed(String sessionId);
}
