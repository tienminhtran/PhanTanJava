package Test;

import dao.impl.GenreDao;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GenreDaoTest {

    private GenreDao genreDao;

    @BeforeAll
    public void setUp() {
        genreDao = new GenreDao();
    }

    @Test
    public void testUpdateGenre() {

        System.out.println(genreDao.listAlbumByGenre("o", 2021));
    }

    @Test
    public void testUpdateGenreFalse() {

        genreDao.getNumberOfAlbumsByGenre().forEach((k, v) -> System.out.println(k + " " + v));
    }

    @AfterAll
    public void tearDown() {
        genreDao = null;
    }

}
