package daoTest;

import dao.AlbumDao;
import dao.ArtistDao;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AlbumDaoTest {

    private AlbumDao albumDao;
    @BeforeAll
    public void init() {
        albumDao = new AlbumDao("tien21010611");
    }

    @Test
    public void testUpdatePriceOfAlum(){
        albumDao.updatePriceOfAlum("A1", 30.2);

    }

    @Test
    public void testLstAlbumByGenre(){

        System.out.println(albumDao.listAlbumByGenre("Rock"));
    }

    @Test
    public void testGetNumberOfAlbumByGenre(){
        System.out.println(albumDao.getNumberOfAlbumByGenre());
    }
    @Test
    public void testSearchAlbumByName(){
        System.out.println(albumDao.searchAlbumByName("Back"));
    }

    @Test
    public void addListArtist(){


    }

    @AfterAll
    public void close() {
        albumDao.close();
    }

}
