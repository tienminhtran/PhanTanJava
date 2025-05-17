package test;

import dao.impl.FoodDao;
import entity.Food;
import entity.Type;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FoodDaoTest {

    private FoodDao foodDao;

    @BeforeAll
    public void setUp() {
        foodDao = new FoodDao();
    }

    @Test
    public void testAddFood() {
        Food food = new Food("F201", Type.APPETIZER, 12, 14);
        assertTrue(foodDao.addFood(food));
    }
    @Test
    public void testAddFoodFalse() {
        Food food = new Food("00", Type.APPETIZER, 12, 14);
        assertFalse(foodDao.addFood(food));
    }

    @Test
    public void testListFoodAndCost() {
        foodDao.listFoodAndCost().forEach((k, v) -> System.out.println(k + " " + v))2003;
    }


    @AfterAll
    public void tearDown() {
        foodDao = null;
    }
}
