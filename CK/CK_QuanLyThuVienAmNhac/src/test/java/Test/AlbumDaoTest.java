package Test;

import dao.impl.AlbumDao;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AlbumDaoTest {

    private AlbumDao albumDao;

    @BeforeAll
    public void setUp() {
        albumDao = new AlbumDao();
    }
    @Test
    public void testUpdatePriceOfAlbum() {
        assertTrue(albumDao.updatePriceOfAlbum("1", 100));
    }

    @Test
    public void testUpdatePriceOfAlbumFalse() {
        assertFalse(albumDao.updatePriceOfAlbum("1", -100));
    }



    @AfterAll
    public void tearDown() {
        albumDao = null;
    }

}
