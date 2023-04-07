package com.bobocode.svydovets.utils;

import com.bobocode.svydovets.entity.Order;
import lombok.experimental.UtilityClass;
import com.bobocode.svydovets.entity.Customer;
import com.bobocode.svydovets.entity.User;

import java.math.BigDecimal;

@UtilityClass
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
