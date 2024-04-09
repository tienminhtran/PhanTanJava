/**
 * @ (#) Test_DaoCustomer.java      3/16/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */
package com.example;
import java.util.Map;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class Test_DaoCustomer1 {
    private Dao_Customer dao_customer;
    @BeforeAll
    public void setUp() {
        dao_customer = new Dao_Customer();
    }

    @Test
    public void testGetQuantityCustomerByCity() {
        Map<String, Integer> map = dao_customer.getNumberCustomerByCity();
        Assertions.assertNotNull(map);
        Assertions.assertEquals(1, map.get("Berlin"));
        Assertions.assertEquals(6, map.get("London"));
    }

    @Test
    public void testGetOrdersByCustomers() {
        Map<Customer, Integer> map = dao_customer.getOrdersByCustomers();
        Assertions.assertNotNull(map);
    }

    @AfterAll
    public void tearDown() {
        dao_customer.close();
    }
}
