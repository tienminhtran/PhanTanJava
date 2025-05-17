package dao;

import java.util.Map;

public interface ReviewDaoImpl {
    public Map<String, Long> countReviewsByBook();

    public boolean updateReview(String isbn, String readerID, int rating, String comment);
}
