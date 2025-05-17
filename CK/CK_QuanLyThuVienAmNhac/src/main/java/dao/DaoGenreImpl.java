package dao;

import entity.Album;

import java.util.List;
import java.util.Map;

public interface DaoGenreImpl {

    public List<Album> listAlbumByGenre(String genreName, int nam);

    public Map<String, Long> getNumberOfAlbumsByGenre();
}
