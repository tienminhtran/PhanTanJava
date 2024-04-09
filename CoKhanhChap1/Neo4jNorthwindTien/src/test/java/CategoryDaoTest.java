
import org.example.Dao.CategoryDao;
import org.example.entity.Category;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(Lifecycle.PER_CLASS)
public class CategoryDaoTest {
	private CategoryDao categoryDao;
	@BeforeAll
	void setup() {
		categoryDao = new CategoryDao();
	}
	
	@Test
	void testFindOneCategory() {
		Category c = categoryDao.findOne("1");
		System.out.println(c);
		assertNotNull(c);
		assertEquals("Beverages", c.getCategoryName());
		assertEquals(true, c.getDescription().contains("Soft drinks"));
	}
	@Test
	void addCategory() {
		CategoryDao categoryDao = new CategoryDao();
		Category category = new Category("72", "sdfsd", "sdfsd", "sdfsd");
		categoryDao.addCategory(category);
		categoryDao.close();
		System.out.println("ok!");
	}

	@Test
	void updateCategory(){
		CategoryDao categoryDao = new CategoryDao();
		Category category = new Category("72", "BBB", "BB", "BBB"	);
		categoryDao.updateCategory(category);
		categoryDao.close();
		System.out.println("ok!");
	}

	@Test
	void deleteCategory(){
		CategoryDao categoryDao = new CategoryDao();
		categoryDao.deleteCategory(72);
		categoryDao.close();
		System.out.println("ok!");
	}


	@AfterAll
	void teardown() {
		categoryDao.close();
		categoryDao = null;
	}
}
