package test;

import dao.impl.ReviewDao;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class ReviewTestDao {
    private ReviewDao reviewDao;

    @BeforeAll
    public void setUp() {
        reviewDao = new ReviewDao();
    }

    @Test
    public void testCountReviewsByBook() {
        System.out.println(reviewDao.countReviewsByBook());

    }

    @Test
    public void testUpdateReview() {
        String isbn = "888-0132350800";
        String readerID = "11";
        int rating = 5;
        String comment = "Great book!";
        System.out.println(reviewDao.updateReview(isbn, readerID, rating, comment));
    }
    @AfterAll
    public void tearDown() {
        reviewDao = null;
    }
}
