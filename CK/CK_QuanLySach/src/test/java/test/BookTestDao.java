package test;

import dao.impl.BookDao;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookTestDao {

    private BookDao bookDao;
    @BeforeAll
    public void setUp() {
        bookDao = new BookDao();
    }
    @Test
    public void testListRatedBooks() {
        String author = "Robert C. Martin";
        int rating = 4;
        System.out.println(bookDao.listRatedBooks(author, rating));
    }



    @AfterAll
    public void tearDown() {
        bookDao = null;
    }

}
