package com.epam.novostroinyi.core.config;

import org.aeonbits.owner.Config;

public interface Configuration extends Config {

  @Key("rp.endpoint")
  String baseUrl();

  @Key("browser.type")
  String browserType();

  @Key("browser.headless")
  @DefaultValue("false")
  boolean headlessMode();

  @Key("rp.username")
  String reportPortalUser();
}
