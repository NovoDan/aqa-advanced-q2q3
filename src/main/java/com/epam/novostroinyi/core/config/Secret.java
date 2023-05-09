package com.epam.novostroinyi.core.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(LoadType.FIRST)
@Sources({"file:src/main/resources/secret.properties", "system:env"})
public interface Secret extends Config {

  @Key("rpPassword")
  String reportPortalPassword();

  @Key("rpToken")
  String reportPortalToken();
}
