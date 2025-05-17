package dao.impl;

import dao.DaoAlbumImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class AlbumDao implements DaoAlbumImpl {
    final EntityManager em;

    final EntityTransaction et;

    public AlbumDao() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ck_quanlythuvienamnhac");
        em = emf.createEntityManager();
        et = em.getTransaction();
    }

    @Override
    public boolean updatePriceOfAlbum(String id, double newPrice) {
        String sql = "update Album a SET a.price = :newPrice WHERE a.id = :id";
        try {
            et.begin();
            if (newPrice > 0) {
                em.createQuery(sql)
                        .setParameter("newPrice", newPrice)
                        .setParameter("id", id)
                        .executeUpdate();
                et.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
