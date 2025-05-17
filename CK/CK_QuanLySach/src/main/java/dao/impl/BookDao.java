package dao.impl;

import dao.BookDaoImpl;
import entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;

public class BookDao  implements BookDaoImpl {
    final EntityManager em;

    final EntityTransaction et;

    public BookDao() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ck_quanlysach");
        em = emf.createEntityManager();
        et = em.getTransaction();
    }

    @Override
    public List<Book> listRatedBooks(String author, int rating) {
//        String sql = "select b FROM Book b " +
//                "join Reviews r " +
//                "on b.ISBN = r.book.id " +
//                "where b.authors=:author and r.rating >= :rating";

        String sql = "select distinct b from Book b join fetch b.reviews r " +
                "where :author member of b.authors and r.rating >= :rating";
        try {
            et.begin();
            List<Book> results = em.createQuery(sql, Book.class)
                    .setParameter("author", author)
                    .setParameter("rating", rating)
                    .getResultList();
            results.forEach(book -> Hibernate.initialize(book.getAuthors()));
            et.commit();
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}