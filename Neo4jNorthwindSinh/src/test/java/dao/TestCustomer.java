/**
 * @ (#) TestCustomer.java      3/10/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package dao;

import dao.DaoCustomer;
import entity.Customer;
import util.AppUtil;
import org.junit.jupiter.api.*;

import java.util.Map;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/10/2024
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCustomer {
    private DaoCustomer daoCustomer;

    @BeforeAll
    public void setup() {
        daoCustomer = new DaoCustomer();
    }

    @Test
    public void testGetNumberCustomerByCity() {
        Map<String, Integer> list = daoCustomer.getNumberCustomerByCity();
        Assertions.assertNotNull(list);
        Assertions.assertEquals(69, list.size());
    }

    @Test
    public void testGetOrdersByCustomers() {
        Map<Customer, Integer> mapCustomer = daoCustomer.getOrdersByCustomers();
        Assertions.assertNotNull(mapCustomer);
        Assertions.assertEquals(89, mapCustomer.size());
    }



    @AfterAll
    public void tearDown() {
        daoCustomer.close();
    }
}
