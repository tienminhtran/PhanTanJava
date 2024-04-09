import org.example.Dao.CustomerDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerDaoTest {
    private CustomerDao customerDao;

    @BeforeAll
    void setup() {
        customerDao = new CustomerDao();
    }

    @Test
    void testGetNumberCustomerByCity() {
        CustomerDao customerDao = new CustomerDao();
        System.out.println(customerDao.getNumberCustomerByCity());

    }

    @AfterAll
    void teardown() {
        customerDao.close();
        customerDao = null;
    }
}
