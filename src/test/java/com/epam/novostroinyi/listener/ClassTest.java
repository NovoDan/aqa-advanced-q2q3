package com.epam.novostroinyi.listener;

public class ClassTest {

  //some useless code
  String aNDMORE;

  public static void error() throws InterruptedException {
    boolean booleanVariable;
    if (booleanMethod()) { /* ... */ }
    if (!booleanMethod()) { /* ... */ }
    if (booleanMethod()) { /* ... */ }
    doSomething(true);
    doSomething(booleanMethod());

    boolean exp = false;

    booleanVariable = booleanMethod();
    booleanVariable = booleanMethod() || exp;
    booleanVariable = !booleanMethod() && exp;
    booleanVariable = !booleanMethod() || exp;
    booleanVariable = booleanMethod() && exp;
    ////xdgcfhvjbknlm,

    String str = null;

    String st = str.replace("a", "");

    switch (str) {
      case "MONDAY":
        break;
      case "TUESDAY":
        foo:for(int i = 0 ; i < 10 ; i++) {  // Noncompliant; the code is correct and behaves as expected but is barely readable
          /* ... */
          break foo;  // this break statement doesn't relate to the nesting case TUESDAY
          /* ... */
        }
        break;
      /* ... */
    }

    switch (str) {
      default:
      case "MONDAY":
      case "TUESDAY":
        String wednesday = "WEDNESDAY";   // Noncompliant; syntactically correct, but behavior is not what's expected
        doSomething(true);
        break;
    }

    //TODO xfchgvjbhknm

    if (doSomething(booleanVariable) | doSomethingFalse()) {
      if (true) {
        if (true) {
          if (true) {
            if (true) {
              if (true) {
                if (true) {
                  if (true) {
                    if (true) {
                      if (true) {
                        if (true) {
                          if (true) {
                            if (true) {
                              if (true) {
                                if (true) {
                                  if (true) {
                                    if (true) {
                                      if (true) {
                                        if (true) {
                                          if (true) {
                                            if (true) {
                                              if (true) {
                                                if (false) {
                                                  return;
                                                }
                                              } else {
                                              }
                                            }
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }

      Thread.sleep(1000);
    }
  }

  static boolean booleanMethod() {
    return true;
  }

  static boolean doSomething(boolean bool) {
    return true;
  }

  static boolean doSomethingFalse() {
    return false;
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    doSomething(true);
    return super.clone();
  }
}
