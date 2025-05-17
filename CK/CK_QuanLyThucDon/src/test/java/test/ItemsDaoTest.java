package test;

import dao.impl.ItemsDao;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ItemsDaoTest {
    private ItemsDao itemsDao;

    @BeforeAll
    public void setUp() {
        itemsDao = new ItemsDao();
    }

    @Test
    public void testListItems() {
        System.out.println(itemsDao.listItems("cha"));
    }


    @AfterAll
    public void tearDown() {
        itemsDao = null;
    }

}
