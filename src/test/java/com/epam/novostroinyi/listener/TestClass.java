package com.epam.novostroinyi.listener;

public class TestClass {

  //some useless code
  String aNDMORE;
  public static void error() {
    boolean booleanVariable;
    if (booleanMethod() == true) { /* ... */ }
    if (booleanMethod() == false) { /* ... */ }
    if (booleanMethod() || false) { /* ... */ }
    doSomething(!false);
    doSomething(booleanMethod() == true);
    
    boolean exp = false; 

    booleanVariable = booleanMethod() ? true : false;
    booleanVariable = booleanMethod() ? true : exp;
    booleanVariable = booleanMethod() ? false : exp;
    booleanVariable = booleanMethod() ? exp : true;
    booleanVariable = booleanMethod() ? exp : false;
    
    if(true) {
      if(true) {
        if(false) return;}}
  }
  
  static boolean booleanMethod() {return true;}
  
  static void doSomething(boolean bool){}
}
