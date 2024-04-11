/**
 * @ (#) DaoOrder.java      4/2/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 4/2/2024
 */
public class DaoOrder {
    private final EntityManager em;
    private final EntityTransaction tx;

    public DaoOrder(EntityManager em, EntityTransaction tx) {
        this.em = em;
        this.tx = em.getTransaction();
    }

    /**
     * 5) Tính tổng tiền của đơn hàng khi biết mã số đơn hàng
     * @param id
     * @return
     */
    public double getTotalOrderById(int id) {
        String query = "select sum(oi.quantity * oi.listPrice*(1 - oi.discount)) from OrderItem oi " +
                "where oi.order.id = :id";
        double totalPrice = 0;
        try {
            tx.begin();
            totalPrice = em.createQuery(query, Double.class)
                    .setParameter("id", id)
                    .getSingleResult();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return totalPrice;

    }
}
