package com.epam.novostroinyi.core.reporter;

import com.epam.novostroinyi.core.exception.CreateInstanceException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseReporter {

  private static Reporter reporter;

  public static synchronized <T extends Reporter> Reporter getInstance(Class<T> clazz) {
    if(Objects.isNull(reporter)) {
      try {
        reporter = (Reporter) clazz.getConstructors()[0].newInstance();
      } catch (InvocationTargetException | InstantiationException | IllegalAccessException ex) {
        throw new CreateInstanceException(String.format("Error while creation instance of %s class:%n", clazz.getName()), ex);
      }
    }
    return reporter;
  }
}
