package com.epam.novostroinyi.core.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(LoadType.MERGE)
@Sources({"system:env", "file:src/main/resources/secret.properties"})
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
