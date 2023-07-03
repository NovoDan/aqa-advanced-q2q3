package com.epam.novostroinyi.core.notification;

import com.epam.novostroinyi.core.config.ConfigUtils;
import com.epam.novostroinyi.core.config.Property;
import java.util.Map;

public class SlackNotifier implements Notifier {

  private static final String SLACK_WEBHOOK_URL = Property.SECRET_PROPERTY.slackWebhookUrl();
  @Override
  public void sendMessage(Map<String, String> params) {
    ConfigUtils.getClient().doPost(SLACK_WEBHOOK_URL, "{ \"text\": \"" + params.get("message") + "\"}");
  }
}
