package com.epam.novostroinyi.core.notification;

import java.util.Map;

public interface Notifier {

  void sendMessage(Map<String, String> params);
}
