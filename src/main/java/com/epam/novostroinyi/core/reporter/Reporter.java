package com.epam.novostroinyi.core.reporter;

import java.io.File;

public interface Reporter {

  void addAttachment(String name, String content);
  void addFileAttachment(String name, File file);

  void reportLog(String message);

  void step(String message);

}
