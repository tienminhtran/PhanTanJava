package org.example.Dao;

import org.example.AppUtils.AppUtils;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CustomerDao {
    private Driver driver;

    public CustomerDao() {
        driver = AppUtils.initDriver();
    }

    public Map<String, Integer> getNumberCustomerByCity() {
        String query = "MATCH (c:Customer) RETURN c.city, count(c) as tong ORDER BY tong";
        try (Session session = driver.session()) {
            Result result = session.run(query);
            Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
            while (result.hasNext()) {
                Record record = result.next();
                String city = record.get("c.city").asString();
                int count = record.get("tong").asInt();
                linkedHashMap.put(city, count);
            }
            return linkedHashMap;
        }
    }
    public void close() {
        driver.close();
    }

    public static void main(String[] args) {
//        CustomerDao customerDao = new CustomerDao();
//        System.out.println(customerDao.getNumberCustomerByCity());
//

    }
}
