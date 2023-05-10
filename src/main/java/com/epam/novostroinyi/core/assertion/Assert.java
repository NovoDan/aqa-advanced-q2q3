package com.epam.novostroinyi.core.assertion;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

public class Assert {

  public static void assertEquals(Object actual, Object expected) {
    assertThat(actual).isEqualTo(expected);
  }
  public static void assertEquals(Object actual, Object expected, String message) {
    assertThat(actual).as(message).isEqualTo(expected);
  }

  public static void assertHasSize(Collection collection, int expectedSize) {
    assertThat(collection).hasSize(expectedSize);
  }

  public static void assertHasSize(Collection collection, int expectedSize, String message) {
    assertThat(collection).as(message).hasSize(expectedSize);
  }
}
