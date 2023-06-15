package com.epam.tamentoring.bo.builder;

import com.epam.tamentoring.bo.ShoppingCart;
import com.epam.tamentoring.bo.UserAccount;

public class UserBuilder {

  private String name;
  private String surname;
  private String dateOfBirth;
  private ShoppingCart shoppingCart;

  public UserBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public UserBuilder setSurname(String surname) {
    this.surname = surname;
    return this;
  }

  public UserBuilder setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
    return this;
  }

  public UserBuilder setShoppingCart(ShoppingCart shoppingCart) {
    this.shoppingCart = shoppingCart;
    return this;
  }

  public UserAccount build() {
    return new UserAccount(name, surname, dateOfBirth, shoppingCart);
  }
}
