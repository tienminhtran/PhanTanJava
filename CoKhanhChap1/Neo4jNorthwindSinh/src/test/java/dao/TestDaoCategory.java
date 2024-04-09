/**
 * @ (#) TestDaoCategory.java      3/5/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package dao;

import dao.DaoCategory;
import entity.Category;
import org.junit.jupiter.api.*;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/5/2024
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestDaoCategory {
    private DaoCategory categoryDao;

    @BeforeAll
    public void setup() {
        categoryDao = new DaoCategory();
    }

    @Test
    public void testAddCategory() {
        Category c = new Category("Soft drinks, coffees, teas, beers, and ales", "Clothing", "A01");
        categoryDao.addCategory(c);
        Category cAdd = categoryDao.findOneByID("A01");
        Assertions.assertNotNull(cAdd);
        Assertions.assertEquals("Clothing", cAdd.getCategoryName());
        Assertions.assertEquals("Soft drinks, coffees, teas, beers, and ales", cAdd.getDescription());
    }

    @Test
    public void testFindCategory() {
        Category c = categoryDao.findOneByID("1");
        Assertions.assertNotNull(c);
        Assertions.assertEquals("Beverages", c.getCategoryName());
        Assertions.assertEquals(true, c.getDescription().contains("Soft drinks"));

    }
    @Test
    public void testFindCategoryNotNull() {
        Category c = categoryDao.findOneByID("A1");
        Assertions.assertEquals(null,c);
    }

    @Test
    public void testDeleteCategory() {
        categoryDao.deleteCategory("A01");
        Category c = categoryDao.findOneByID("A01");
        Assertions.assertEquals(null, c);
    }

    @AfterAll
    public void teardown() {
        categoryDao.close();
        categoryDao = null;
    }

}
