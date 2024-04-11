/*
 * @ {#} GenericDAO.java   1.0     02/04/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   02/04/2024
 * @version:    1.0
 */
public abstract class GenericDAO<T> {
    protected EntityManager em;

    public GenericDAO() {
        em= Persistence.createEntityManagerFactory("JPA_MSSQL").createEntityManager();
    }
    public boolean add(T t) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(t);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }
}
