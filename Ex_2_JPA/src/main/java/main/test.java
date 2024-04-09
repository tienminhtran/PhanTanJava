package main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex2");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();


            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        emf.close();
    }

}
