package dao.impl;

import dao.ReviewDaoImpl;
import entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReviewDao implements ReviewDaoImpl {
    final EntityManager em;

    final EntityTransaction et;

    public ReviewDao() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ck_quanlysach");
        em = emf.createEntityManager();
        et = em.getTransaction();
    }

    @Override
    public Map<String, Long> countReviewsByBook() {
        String sql = "select ba.author, count(*)  tong FROM books b\n" +
                "join book_translations bt\n" +
                "on b.ISBN = bt.ISBN\n" +
                "join books_authors ba\n" +
                "on b.ISBN = ba.ISBN\n" +
                "group by ba.ISBN, ba.author order by  ba.author desc";
        Map<String, Long> map = new LinkedHashMap<>();
        List<Object[]> list = em.createNativeQuery(sql).getResultList();
        list.forEach(a -> {
            String author = (String) a[0];
            Long count = ((Integer) a[1]).longValue();
            map.put(author, count);
        });
        return map;
    }

    @Override
    public boolean updateReview(String isbn, String readerID, int rating, String comment) {
        String query = "update Reviews r set r.rating =:rating, r.comment = :comment where r.book.id = :isbn and r.person.id = :readerID";
        try {
            et.begin();
            Book book = em.find(Book.class, isbn);
            if(comment != null && rating >=1 && rating <= 5) {
                em.createQuery(query)
                        .setParameter("rating", rating)
                        .setParameter("comment", comment)
                        .setParameter("isbn", isbn)
                        .setParameter("readerID", readerID)
                        .executeUpdate();
                et.commit();
                return true;
            }
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
