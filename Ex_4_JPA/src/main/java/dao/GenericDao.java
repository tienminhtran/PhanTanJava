package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public abstract class GenericDao<T> {
    private EntityManager em;
    public GenericDao() {
        em = Persistence.createEntityManagerFactory("ex4").createEntityManager();
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
            return false;
        }
    }

    public boolean update(T t) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(t);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            return false;
        }
    }

    public boolean delete(T t) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(t);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            return false;
        }
    }




}
