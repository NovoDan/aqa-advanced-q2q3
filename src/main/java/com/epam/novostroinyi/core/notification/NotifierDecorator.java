package com.epam.novostroinyi.core.notification;

import java.util.Map;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotifierDecorator implements Notifier {

  private Notifier wrapper;

  public NotifierDecorator(Notifier notifier) {
    this.wrapper = notifier;
  }

  @Override
  public void sendMessage(Map<String, String> params) {
    wrapper.sendMessage(params);
  }
}
