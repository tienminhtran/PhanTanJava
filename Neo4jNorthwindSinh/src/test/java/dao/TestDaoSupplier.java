package dao;

import dao.DaoSupplier;
import entity.Supplier;
import util.AppUtil;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestDaoSupplier {
//    private static final String DB_NAME = "neo4j";

    private DaoSupplier supplierDao;
    @BeforeAll
    public void setup() {
        supplierDao = new DaoSupplier();
    }

    @Test
    public void testAddSupplier() {
        Supplier s = new Supplier("S1", "ABC", "John", "Manager", "123", "HCM");

        Supplier sAdd = supplierDao.addSupplier(s);
        Assertions.assertNotNull(sAdd);
        Assertions.assertEquals("S1", sAdd.getSupplierID());
        Assertions.assertEquals("ABC", sAdd.getCompanyName());
        Assertions.assertEquals("John", sAdd.getContactName());
        Assertions.assertEquals("Manager", sAdd.getContactTitle());
        Assertions.assertEquals("123", sAdd.getAddress());
        Assertions.assertEquals("HCM", sAdd.getCity());
    }

    @Test
    public void testFindSupplier() {
        Supplier s = supplierDao.findOneByID("11");
        Assertions.assertNotNull(s);
        Assertions.assertEquals("11", s.getSupplierID());
        Assertions.assertEquals(true, s.getCompanyName().contains("Heli Süßwaren"));
    }


    @AfterAll
    public void teardown() {
        supplierDao.close();
        supplierDao = null;
    }

}
