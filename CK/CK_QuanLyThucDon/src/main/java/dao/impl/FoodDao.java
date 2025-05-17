package dao.impl;

import dao.FoodDaoImpl;
import entity.Food;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FoodDao implements FoodDaoImpl {

    final EntityManager em;

    final EntityTransaction et;

    public FoodDao() {
        EntityManagerFactory emf = jakarta.persistence.Persistence.createEntityManagerFactory("ck_quanlythucdon");
        em = emf.createEntityManager();
        et = em.getTransaction();
    }

    @Override
    public boolean addFood(Food food) {
        try {
            et.begin();
            if (food.getId().matches("F[0-9]{3}")) {
                em.persist(food);
                et.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            et.rollback();
        }
        return false;
    }

    @Override
    public Map<Food, Double> listFoodAndCost() {
        String sql = "select f.id, sum(ing.price * ing.quantity) + (f.preparation_time + f.serving_time)*0.2 as giaGoc \n" +
                "from items it\n" +
                "join items_ingredients ii\n" +
                "on ii.item_id = it.id\n" +
                "join ingredients ing\n" +
                "on ing.ingredient_id = ii.ingredient_id\n" +
                "join foods f on f.id = it.id\n" +
                "group by f.id, f.preparation_time , f.serving_time order by giaGoc desc";
        try {
            et.begin();
            List<Object[]> list = em.createNativeQuery(sql).getResultList();
            Map<Food, Double> foodCost = new LinkedHashMap<>();
            for (Object[] obj : list) {
                Food food = em.find(Food.class, obj[0]);
                foodCost.put(food, (Double) obj[1]);
            }
            et.commit();
            return foodCost;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
