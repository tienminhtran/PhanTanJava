package main;

import org.example.entity.Groups;
import org.example.entity.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GroudDBSQL");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
//            Users user1 = new Users("Nguyen Van A", "123456", "nguyenA@gmail.com");
//            Users user2 = new Users("Nguyen Van B", "123433DCWE56", "nguyenB@gmail.com");
//            Users user3 = new Users("Nguyen Thi C", "33ED", "nguyenC@gmail.com");
//            Users user4 = new Users("Nguyen Van F", "DD2E23E", "nguyenF@gmail.com");
//            Users user5 = new Users("Nguyen Van D", "1111", "nguyenD@gmail.com");
//
//            em.persist(user1);
//            em.persist(user2);
//            em.persist(user3);
//            em.persist(user4);
//            em.persist(user5);
//
//            Groups group1 = new Groups("Group 1");
//            Groups group2 = new Groups("Group 2");
//            Groups group3 = new Groups("Group 3");
//
//            em.persist(group1);
//            em.persist(group2);
//            em.persist(group3);
            String sql = "INSERT INTO [dbo].[users_groups] values(7,2)";
            int n = em.createNativeQuery(sql).executeUpdate();
            System.out.println(n);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        emf.close();
    }
}
