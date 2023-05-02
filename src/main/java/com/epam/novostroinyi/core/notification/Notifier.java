package com.epam.novostroinyi.core.notification;

public interface Notifier {

  void sendMessage(String... args);
}
