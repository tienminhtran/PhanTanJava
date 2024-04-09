/**
 * @ (#) TestDaoProduct.java      3/9/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package dao;

import dao.DaoProduct;
import entity.Product;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/9/2024
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestDaoProduct {
    private DaoProduct daoProduct;

    @BeforeAll
    public void setup() {
        daoProduct = new DaoProduct();
    }

    @Test
    public void testListProductByCategory() {
        String categoryName = "Beverages";
        List<Product> products = daoProduct.listProductByCategory(categoryName);
        Assertions.assertNotNull(products);
        Assertions.assertEquals(12, products.size());

    }

    @Test
    public void testListProductMaxPrice() {

        List<Product> products = daoProduct.listProductMaxPrice();
        Assertions.assertNotNull(products);
        products.forEach(p -> {
            Assertions.assertEquals(263.5, p.getUnitPrice());
            Assertions.assertTrue(p.getUnitPrice() >= 263);
        });
    }

    @Test
    public void testGetTotalProduct() {
        Map<String, Integer> totalProduct = daoProduct.getTotalProduct();
        Assertions.assertNotNull(totalProduct);
        Assertions.assertEquals(77, totalProduct.size());

    }

    @Test
    public void testFindProductByName() {
        String productName = "Chai";
        List<Product> p = daoProduct.findProductByName(productName);
        Assertions.assertNotNull(p);
        p.forEach(product -> {
            Assertions.assertEquals("Chai", product.getProductName());
            Assertions.assertEquals(39, product.getUnitsInStock());
        });
    }

    @AfterAll
    public void teardown() {
        daoProduct.close();
    }

}
