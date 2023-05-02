package com.epam.novostroinyi.core.reporter;

public interface Reporter {

  void addAttachment(String name, String content);

  void reportLog(String message);

  void step(String message);

}
