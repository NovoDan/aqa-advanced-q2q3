package com.epam.tamentoring.bo.builder;

import com.epam.tamentoring.bo.Product;

public class ProductBuilder {

  private int id;
  private String name;
  private double price;
  private double quantity;

  public Product build() {
    return new Product(id, name, price, quantity);
  }

  public ProductBuilder setId(int id) {
    this.id = id;
    return this;
  }

  public ProductBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public ProductBuilder setPrice(double price) {
    this.price = price;
    return this;
  }

  public ProductBuilder setQuantity(double quantity) {
    this.quantity = quantity;
    return this;
  }
}
