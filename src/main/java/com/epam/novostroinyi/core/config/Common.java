package com.epam.novostroinyi.core.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(LoadType.MERGE)
@Sources({"system:env", "file:src/main/resources/configuration.properties"})
public interface Common extends Config {

  @Key("rp.endpoint")
  String baseUrl();

  @Key("browser")
  String browserType();

  @Key("rp.username")
  String reportPortalUser();

  @Key("suite")
  String suite();

  @Key("makeScreenshot")
  boolean makeScreenshot();

  @Key("rp.project.name")
  String rpProjectName();

  @Key("remote.browser.url")
  String remoteBrowserUrl();

  @Key("remote")
  Boolean isRemote();

  @Key("remote.client.username")
  String remoteClientUsername();

  @Key("testrail.api.url")
  String testRailApiUrl();

  @Key("testrail.api.login")
  String testRailApiLogin();
}
