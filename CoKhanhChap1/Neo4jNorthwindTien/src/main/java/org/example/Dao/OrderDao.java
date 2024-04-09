package org.example.Dao;

import org.example.AppUtils.AppUtils;
import org.example.entity.Customer;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class OrderDao {
    private Driver driver;

    public OrderDao() {
        driver = AppUtils.initDriver();
    }


    /**
     *
     * Tinh tong tien cua don hang khi biet ma don hang
     *
     * @param maDonHang
     * @return double
     */
    public double tongTienCuaDonHang(String maDonHang) {
        String q = "match (o:Order)-[R:ORDERS]->(p:Product) WHERE R.orderID=$orderID return sum(toFloat(R.unitPrice) * R.quantity) as tongTien";

        Map<String, Object> map = Map.of("orderID", maDonHang);
        try (Session session = driver.session()) {
            Result result = session.run(q, map);
            if (result.hasNext()) {
                Record record = result.next();
                return record.get("tongTien").asNumber().doubleValue();
            }
        }

        return 0;
    }

    /**
     * CACH1 DUNG MAP(Customer, Integer)
     * Tinh tong tien cua don hang khi biet ma don hang
     *
     * @return
     */

    public Map<Customer, Integer> getTotalsProduct() {
        String q1 = " MATCH (c:Customer)-[:PURCHASED]->(o:Order) RETURN c, count(o) as totalOrders";

        Map<Customer, Integer> map = new HashMap<>();
        try (Session session = driver.session()) {
            Result result = session.run(q1);
            while (result.hasNext()) {
                Record record = result.next();
                Node node = record.get("c").asNode();
                Customer customer = new Customer(
                        node.get("customerID").asString(),
                        node.get("companyName").asString(),
                        node.get("contactName").asString(),
                        node.get("contactTitle").asString(),
                        node.get("address").asString(),
                        node.get("city").asString());
                int totalOrders = record.get("totalOrders").asInt();
                map.put(customer, totalOrders);
            }
        }
        return map;
    }

    /**
     * CACH2 DUNG MAP(String, Integer)
     */
    public Map<String, Integer> getTotalsProduct2() {
        String q1 = " MATCH (c:Customer)-[:PURCHASED]->(o:Order) RETURN c.customerID as customerID, count(o) as totalOrders";

        Map<String, Integer> map = new HashMap<>();
        try (Session session = driver.session()) {
            Result result = session.run(q1);
            while (result.hasNext()) {
                Record record = result.next();
                String customerID = record.get("customerID").asString();
                int totalOrders = record.get("totalOrders").asInt();
                map.put(customerID, totalOrders);
            }
        }
        return map;

    }

    /**
     * query :"MATCH (n:Order)
     * WHERE date(substring(n.orderDate, 0, 10)) = date("1996-07-04")
     * RETURN n"
     */
    public double tongHoaDonCuaNgay(LocalDate ngay) {

        String q = "MATCH (o:Order)-[r:ORDERS]->(p:Product) \n" +
                "WHERE date(substring(o.orderDate, 0, 10)) = date($date)\n" +
                "RETURN sum(toFloat(r.unitPrice) * r.quantity) as n";
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Map<String, Object> map = Map.of("date", dft.format(ngay));
        try (Session session = driver.session()) {
            Result result = session.run(q, map);
            if (result.hasNext()) {
                Record record = result.next();
                return record.get("n").asNumber().doubleValue();
            }
        }
        return 0.0;
    }

    /**
     * MATCH (o:Order)-[r:ORDERS]->(p:Product)
     * RETURN date(substring(o.orderDate, 0, 10)).month , date(substring(o.orderDate, 0, 10)).year ,
     *        sum(toFloat(r.unitPrice) * r.quantity) AS n
     *        TỔNG HOÓA DON THEO THÁNG/ NĂM
     */
    public Map<String, Double> tongHoaDonTheoThang() {
        String q = "MATCH (o:Order)-[r:ORDERS]->(p:Product)\n" +
                "RETURN date(substring(o.orderDate, 0, 10)).month as month, date(substring(o.orderDate, 0, 10)).year as year, sum(toFloat(r.unitPrice) * r.quantity) AS n";
        Map<String, Double> map = new HashMap<>();
        try (Session session = driver.session()) {
            Result result = session.run(q);
            while (result.hasNext()) {
                Record record = result.next();
                int month = record.get("month").asInt();
                int year = record.get("year").asInt();
                double tongTien = record.get("n").asNumber().doubleValue();
                map.put("Thang:" +month + ", nam: " + year+ " Tong tien la: ", tongTien);
            }
        }
        return map;
    }
    public void close() {
        driver.close();
    }

    public static void main(String[] args) {
        OrderDao orderDao = new OrderDao();
//        System.out.println(orderDao.tongTienCuaDonHang("10248"));
//      Tinh tong tien cua don hang khi biet ma don hang

//        Map<String, Integer> map = new OrderDao().getTotalsProduct();
//        System.out.println(map);

//        Map<Customer, Integer> map = new OrderDao().getTotalsProduct();
//        for (Map.Entry<Customer, Integer> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + " " + "totalOrders: " + entry.getValue());
//        }

//        System.out.println(orderDao.tongHoaDonCuaNgay(LocalDate.of(1996, 7, 4)));

        Map<String, Double> map = orderDao.tongHoaDonTheoThang();
        System.out.println(map);




    }
}
