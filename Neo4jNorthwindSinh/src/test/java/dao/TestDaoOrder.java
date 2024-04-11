/**
 * @ (#) TestDaoOrder.java      3/11/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package dao;

import dao.DaoOrder;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/11/2024
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestDaoOrder {
    private DaoOrder daoOrder;

    @BeforeAll
    public void setup() {
        daoOrder = new DaoOrder();
    }

    @Test
    public void testSumPriceOrderByID() {
        String orderByID = "10254";
        double sumPriceOrder = daoOrder.sumPriceOrderByID(orderByID);
        Assertions.assertEquals(200.0, sumPriceOrder);
    }

    @Test
    public void testSumPricerOrderByDateOrder() {
        LocalDate date = LocalDate.of(1996, 7, 4);
        double sumPriceOrder = daoOrder.sumPricerOrderByDateOrder(date);
        Assertions.assertEquals(630.0, sumPriceOrder);
    }

    @AfterAll
    public void tearDown() {
        daoOrder.close();
    }
}
