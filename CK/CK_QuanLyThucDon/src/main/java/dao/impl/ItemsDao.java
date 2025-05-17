package dao.impl;

import dao.ItemsDaoImpl;
import entity.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;

public class ItemsDao implements ItemsDaoImpl{
    final EntityManager em;

    final EntityTransaction et;

    public ItemsDao() {
        EntityManagerFactory emf = jakarta.persistence.Persistence.createEntityManagerFactory("ck_quanlythucdon");
        em = emf.createEntityManager();
        et = em.getTransaction();
    }
    @Override
    public List<Item> listItems(String supplierName) {
        String sql = "select it from Item it\n" +
                "join ItemIngredient ii\n" +
                "on ii.item.id = it.id\n" +
                "join Ingredient ing\n" +
                "on ing.id = ii.ingredient.id\n" +
                "where it.onSpecial = true and ing.supplierName like :supplierName";
        try {
            et.begin();
            List<Item> items = em.createQuery(sql, Item.class)
                    .setParameter("supplierName", "%"+ supplierName + "%")
                    .getResultList();
            et.commit();
            return items;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
