package dao;

import entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class CategoryDao {
    private EntityManager em;

    private EntityTransaction et;

    public CategoryDao(EntityManager em) {
        this.em = em;
    }


    public boolean addCategory(Category category) {
        try {
            et = em.getTransaction();
            et.begin();

            em.createNamedQuery("Category.addCategory")
                    .setParameter("name", category.getName())
                    .setParameter("id", category.getId())
                    .executeUpdate();

            et.commit();
            return true;
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateCategory(int ma) {
        try {
            et = em.getTransaction();
            et.begin();

            em.createNamedQuery("Category.updateCategory")
                    .setParameter("name", "Coca Cola")
                    .setParameter("id", ma)
                    .executeUpdate();

            et.commit();
            return true;
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            e.printStackTrace();
            return false;
        }

    }

    public boolean deleteCategory(int ma) {
        try {
            et = em.getTransaction();
            et.begin();

            em.createNamedQuery("Category.deleteCategory")
                    .setParameter("id", ma)
                    .executeUpdate();

            et.commit();
            return true;
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public Category findIdCategory(int ma) {
        try {
            return (Category) em.createNamedQuery("Category.findIdCategory")
                    .setParameter("id", ma)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Category> getAllCategories() {
        try {
            return em.createNamedQuery("Category.getAllCategory", Category.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        EntityManager em = jakarta.persistence.Persistence.createEntityManagerFactory("ex5").createEntityManager();
        CategoryDao cateoryDao = new CategoryDao(em);
//        Category category = new Category(8, "Beverages");
//        cateoryDao.addCategory(category);

//        cateoryDao.updateCategory(8);

//        cateoryDao.deleteCategory(8);

//        System.out.println(cateoryDao.findIdCategory(7));



        List<Category> categories = cateoryDao.getAllCategories();
        for (Category category : categories) {
            System.out.println(category);
        }
    }


}
