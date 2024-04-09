import org.example.Dao.OrderDao;
import org.example.entity.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;
import java.util.Map;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderDaoTest {
    private OrderDao orderDao;

    @BeforeAll
    void setup() {
        orderDao = new OrderDao();
    }


    @Test
    public void testTongTienCuaDonHang() {
        OrderDao orderDao = new OrderDao();
        System.out.println(orderDao.tongTienCuaDonHang("10248"));
    }

    /**
     * getTotalsProduct
     */
    @Test
    public void testGetTotalsProduct() {
        OrderDao orderDao = new OrderDao();
        System.out.println(orderDao.getTotalsProduct());
//        Map<Customer, Integer> map = new OrderDao().getTotalsProduct();
//        for (Map.Entry<Customer, Integer> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + " "+"totalOrders: " + entry.getValue());
//        }
    }

    /**
     * Tinh tong so tien hoa don trong ngay 1996-7-4
     */
    @Test
    public void testTongHoaDonCuaNgay() {
        OrderDao orderDao = new OrderDao();
        System.out.println(orderDao.tongHoaDonCuaNgay(LocalDate.of(1996, 7, 4)));
    }

    /**
     * Tinh tong hoa don theo thang/NAM
     */
    @Test
    public void testTongHoaDonTheoThang() {
        OrderDao orderDao = new OrderDao();
        Map<String, Double> map = orderDao.tongHoaDonTheoThang();
        for(Map.Entry<String, Double> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }

    @AfterAll
    void teardown() {
        orderDao.close();
        orderDao = null;
    }

}
