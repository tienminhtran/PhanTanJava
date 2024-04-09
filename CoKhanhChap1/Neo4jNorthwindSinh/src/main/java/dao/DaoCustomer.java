/**
 * @ (#) DaoCustomer.java      3/10/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package dao;

import entity.Customer;
import util.AppUtil;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/10/2024
 */
public class DaoCustomer {
    private Driver driver;

    public DaoCustomer() {
        driver = AppUtil.getDriver();
    }

    /**
     * get number customer by city
     * @return
     */
    public Map<String, Integer> getNumberCustomerByCity() {
        String query = "match (cu:Customer) \n" +
                "return cu.city, count(*) as sl \n" +
                "order by sl desc";

        try (Session session = driver.session()) {
            return session.executeRead(tx -> {
                Result rs = tx.run(query);
                if(!rs.hasNext()){
                  return null;
                }

                Map<String, Integer> hasMap = new LinkedHashMap<>();
                rs.stream()
                        .forEach(record -> {
                            String n = record.get("cu.city").asString();
                            int sl = record.get("sl").asInt();
                            hasMap.put(n, sl);
                        });
                return hasMap;
//                        .map(record -> {
//                            String n = record.get("cu.city").asString();
//                            int sl = record.get("sl").asInt();
//                            return hasMap.put(n, sl);
//                        });

            });
        }


    }

    /**
     * Dem so luong don hang cua tung khach hang
     * @return
     */
    public Map<Customer, Integer> getOrdersByCustomers() {
        String query = "match (cus:Customer)-[:PURCHASED]->(o:Order)\n" +
                "return cus, count(o.orderID) as slOrder";
        try (Session session = driver.session()) {
            return session.executeRead(tx -> {
               Result rs = tx.run(query);
               if(!rs.hasNext()) {
                   return null;
               }
               Map<Customer, Integer>  map = new HashMap<Customer, Integer>();
               rs.stream().forEach(record -> {
                   Node node = record.get("cus").asNode();
                   Customer customer = new Customer(node.get("customerID").asString(), node.get("contactName").asString(), node.get("companyName").asString(), node.get("address").asString(), node.get("city").asString());
                   int slOrder = record.get("slOrder").asInt();
                   map.put(customer, slOrder);

               });
               return map;
            });
        }
    }

    public void close() {
        driver.close();
    }

    public static void main(String[] args) {
        DaoCustomer daoCustomer = new DaoCustomer();
        Map<Customer, Integer> mapCustomer = daoCustomer.getOrdersByCustomers();
        mapCustomer.forEach((k,v) -> {
            System.out.println(k +"\n Co so luong don hang: " + v + "\n");
        });

    }

}
