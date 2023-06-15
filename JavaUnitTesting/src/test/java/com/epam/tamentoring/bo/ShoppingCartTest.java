package com.epam.tamentoring.bo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.epam.tamentoring.bo.builder.ProductBuilder;
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
    Product product = new ProductBuilder()
        .setId(1)
        .setName("bread")
        .setPrice(1.0)
        .setQuantity(1)
        .build();
    products.add(product);
  }

  @Test
  public void addItemToCartTest() {
    ShoppingCart cart = new ShoppingCart(products);
    int cartItemsBefore = cart.getProducts().size();
    cart.addProductToCart(
        new ProductBuilder()
            .setId(2)
            .setName("ham")
            .setPrice(2.0)
            .setQuantity(1)
            .build());
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
    products.add(new ProductBuilder()
        .setId(2)
        .setName("ham")
        .setPrice(2.2)
        .setQuantity(5)
        .build());
    ShoppingCart cart = new ShoppingCart(products);
    AtomicReference<Double> expectedTotalPrice = new AtomicReference<>((double) 0);
    products.forEach(product -> expectedTotalPrice.updateAndGet(
        v -> (v + product.getPrice() * product.getQuantity())));
    assertEquals(expectedTotalPrice.get(), cart.getCartTotalPrice());
  }

}
