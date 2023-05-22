package com.epam.tamentoring.bo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShoppingCartTest {

  private List<Product> products;

  @BeforeEach
  public void addProduct() {
     products = new ArrayList<>();
    Product product = new Product(1, "bread", 1.0, 1);
    products.add(product);
  }

  @Test
  public void addItemToCartTest() {
    ShoppingCart cart = new ShoppingCart(products);
    int cartItemsBefore = cart.getProducts().size();
    cart.addProductToCart(new Product(2, "ham", 2.0, 1));
    Assertions.assertNotEquals(cartItemsBefore, cart.getProducts().size());
  }

  @Test
  public void removeItemFromCartTest() {
    ShoppingCart cart = new ShoppingCart(products);
    int cartItemsBefore = cart.getProducts().size();
    cart.removeProductFromCart(products.get(0));
    Assertions.assertNotEquals(cartItemsBefore, cart.getProducts().size());
  }

  @Test
  public void getTotalPriceTest() {
    products.add(new Product(2, "ham", 2.2, 5));
    ShoppingCart cart = new ShoppingCart(products);
    AtomicReference<Double> expectedTotalPrice = new AtomicReference<>((double) 0);
    products.forEach(product -> expectedTotalPrice.updateAndGet(
        v -> (v + product.getPrice() * product.getQuantity())));
    assertEquals(expectedTotalPrice.get(), cart.getCartTotalPrice());
  }

}
