package org.example;

import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.entity.User;

import java.math.BigDecimal;

public class DataFactory {

    public static final long DEFAULT_ID = 999L;

    public static User getDefaultUserWithoutId() {
        return new User(null, "John", "Doe");
    }

    public static Order getDefaultOrderWithoutId() {
        return new Order(null, "Order", BigDecimal.TEN);
    }

    public static Customer getDefaultCustomerWithoutId() {
        return new Customer(null, "John", "Doe", "123");
    }
}
