package daoTest;

import dao.ArtistDao;
import entity.Aritst;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArtistDaoTest {
    private ArtistDao artistDao;
    @BeforeAll
    public void init() {
        artistDao = new ArtistDao("tien21010611");
    }

    @Test
    public void testAddArtist() {
        artistDao.addArtist(new Aritst("21010611", "Tien", LocalDate.of(2003, 1, 1), "https://www.facebook.com"));
    }


    @Test
    public void testFindArtistById() {

    }
    @AfterAll
    public void close() {
        artistDao.close();
    }
}
