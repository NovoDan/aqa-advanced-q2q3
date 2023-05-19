package com.epam.tamentoring.bo;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
import org.mockito.exceptions.verification.TooFewActualInvocations;

public class OrderServiceTest {

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
    Product product = new Product(1, "bread", 10.0, 1);
    products.add(product);
    ShoppingCart cart = new ShoppingCart(products);

    UserAccount userAccount = new UserAccount("John", "Smith", "1990/10/10", cart);
    Mockito.when(discountUtility.calculateDiscount(userAccount)).thenReturn(3.0);

    Assertions.assertEquals(orderService.getOrderPrice(userAccount), discount, 7.0);
    verify(discountUtility, times(1)).calculateDiscount(userAccount);
    Assertions.assertThrows(MockitoException.class,
        () -> verify(discountUtility).toString());
    Mockito.verifyNoMoreInteractions(discountUtility);
  }

}
