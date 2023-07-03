package com.epam.novostroinyi.core.notification;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SlackDecorator extends NotifierDecorator {

  public SlackDecorator(Notifier notifier) {
    super(notifier);
  }

}
