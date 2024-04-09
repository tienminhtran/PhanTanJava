import org.example.Dao.CategoryDao;
import org.example.Dao.ProductDao;
import org.example.entity.Category;
import org.example.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class ProductDaoTest {
    private ProductDao productDao;

    @BeforeAll
    void setup() {
        productDao = new ProductDao();
    }

    @Test
    void testFindListProduct_Supplier() {
        ProductDao productDao = new ProductDao();
        for (Product product : productDao.findListProduct_Supplier("Leka Trading")) {
            System.out.println(product);
        }
        productDao.close();

    }


    @Test
    void upDate_Pro() {
        ProductDao productDao = new ProductDao();
        Product product = new Product("38", "Chai", null, null, "10 boxes x 20 bags", 1263.5);
        productDao.upDate_Pro(product);
        productDao.close();
        System.out.println("ok!");
    }

    @Test
    void findProductByPriceMax() {
        ProductDao productDao = new ProductDao();
        productDao.findProductByPriceMax();
        productDao.close();
    }
    @AfterAll
    void teardown() {
        productDao.close();
        productDao = null;
    }
}
