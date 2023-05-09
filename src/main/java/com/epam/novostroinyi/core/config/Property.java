package com.epam.novostroinyi.core.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.aeonbits.owner.ConfigFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Property {

  public static final Secret SECRET_PROPERTY = ConfigFactory.create(Secret.class);
  public static final Common COMMON_PROPERTY = ConfigFactory.create(Common.class);
}
