package test;

import dao.impl.CustomerDao;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestDaoCustomer {
    private CustomerDao customerDao;
    @BeforeAll
    public void init(){
        customerDao = new CustomerDao();
    }
    @Test
    public void testListCustoersByCheckMonthYear(){
        System.out.println(customerDao.listCustoersByCheckMonthYear("Single", 4, 2024));
    }

    @AfterAll
    public void destroy(){
        customerDao = null;
    }

}
