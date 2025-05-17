package dao.impl;

import dao.ImplDaoPositions;
import entity.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class DaoPositions implements ImplDaoPositions {

    private final EntityManager em;
    private final EntityTransaction et;

    public DaoPositions() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ck_quanlytuyendung");
        em = emf.createEntityManager();
        et = em.getTransaction();
    }

    @Override
    public List<Position> listPositions(String name, double salaryFrom, double salaryTo) {
        String sql = "SELECT * FROM positions p " +
                "WHERE p.name LIKE ?1 " +
                "AND p.salary >= ?2 AND p.salary <= ?3 "+
                "ORDER BY p.name";

        List<Position> listPositions = new LinkedList<>();
        try {
            et.begin();
            listPositions = em.createNativeQuery(sql, Position.class)
                    .setParameter(1, "%" + name + "%")
                    .setParameter(2, salaryFrom)
                    .setParameter(3, salaryTo)
                    .getResultList();
            et.commit();
            return listPositions;
        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
        }
        return null;
    }

}
