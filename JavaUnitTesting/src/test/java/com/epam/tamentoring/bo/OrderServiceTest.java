package com.epam.tamentoring.bo;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.epam.tamentoring.bo.builder.ProductBuilder;
import com.epam.tamentoring.bo.builder.UserBuilder;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;

public class OrderServiceTest {

  private int expectedNumberOfInvocations = 1;

  @Mock
  DiscountUtility discountUtility;

  @InjectMocks
  OrderService orderService = new OrderService();

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void verifyDiscountIsProvided() {
    double discount = 3.0;
    List<Product> products = new ArrayList<>();
    Product product = new ProductBuilder()
        .setId(1)
        .setName("bread")
        .setPrice(10.0)
        .setQuantity(1)
        .build();
    products.add(product);
    ShoppingCart cart = new ShoppingCart(products);

    UserAccount userAccount = new UserBuilder()
        .setName("John")
        .setSurname("Smith")
        .setDateOfBirth("1990/10/10")
        .setShoppingCart(cart).build();
    Mockito.when(discountUtility.calculateDiscount(userAccount)).thenReturn(3.0);

    Assertions.assertEquals(orderService.getOrderPrice(userAccount), discount, 7.0);
    verify(discountUtility, times(expectedNumberOfInvocations)).calculateDiscount(userAccount);
    Assertions.assertThrows(MockitoException.class,
        () -> verify(discountUtility).toString());
    Mockito.verifyNoMoreInteractions(discountUtility);
  }

}
