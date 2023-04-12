package com.epam.novostroinyi.core.notification;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotifierDecorator implements Notifier {

  private Notifier wrapper;

  public NotifierDecorator(Notifier notifier) {
    this.wrapper = notifier;
  }

  @Override
  public void sendMessage(String ... messageParams) {
    wrapper.sendMessage(messageParams);
  }
}
