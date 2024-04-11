/**
 * @ (#) DaoCustomer.java      4/2/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package dao;

import entities.Customer;
import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 4/2/2024
 */
public class DaoCustomer {
    private final EntityManager em;
    private final EntityTransaction tx;

    public DaoCustomer(EntityManager em, EntityTransaction tx) {
        this.em = em;
        this.tx = em.getTransaction();
    }

    /**
     * Insert a customer
     * @param customer
     * @return
     */
    public boolean insert(Customer customer) {
        return false;
    }

    /**
     * Update a customer
     * @param customer
     * @return
     */
    public boolean update(Customer customer) {
        return false;
    }

    /**
     * Thống kê số khách hàng theo từng bang
     * @return
     */
    public Map<String, Integer> getNumberCustomerByState() {
        String query = "select state, COUNT(customer_id) from customers \n" +
                "group by state";
//        String query = "select c.address.state, COUNT(c.id) as sl from Customer c group by c.address.state";
        try {
            tx.begin();
            Map<String, Integer> resultMap = new HashMap<>();
            Stream result = em.createNativeQuery(query).getResultStream();
            result.forEach(item -> {
                Object[] obj = (Object[]) item;
                resultMap.put((String) obj[0], (Integer) obj[1]);
            });
            tx.commit();
            return resultMap;
        } catch (Exception e) {
            tx.rollback();
            return null;
        }
    }

//    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPADemo_SQL");
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        DaoCustomer daoCustomer = new DaoCustomer(em, tx);
//        Map<String, Integer> map = daoCustomer.getNumberCustomerByState();
//        map.forEach((key, value) -> {
//            System.out.println(key + " : " + value);
//        });
//    }
}
