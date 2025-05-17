package dao.impl;

import dao.DaoGenreImpl;
import entity.Album;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GenreDao implements DaoGenreImpl {
    final EntityManager em;

    final EntityTransaction et;

    public GenreDao() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ck_quanlythuvienamnhac");
        em = emf.createEntityManager();
        et = em.getTransaction();
    }
    @Override
    public List<Album> listAlbumByGenre(String genreName, int nam) {
        String sql = "Select g from Genre g join Album a on g.id = a.genre.id\n" +
                "where g.name like :genreName and a.yearOfRelease = :nam";
        try{
            et.begin();
            List<Album> albums = em.createQuery(sql)
                            .setParameter("genreName", "%" + genreName + "%")
                            .setParameter("nam", nam)
                            .getResultList();

            et.commit();
            return albums;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, Long> getNumberOfAlbumsByGenre() {
        String sql = "Select g.name, count(*) from Genre g join Album a on g.id = a.genre.id\n" +
                "group by g.name";
        Map<String, Long> map = new LinkedHashMap<>();

        try{
            et.begin();
            List<Object[]> results = em.createQuery(sql).getResultList();
            for (Object[] result : results) {
                String genreName = (String) result[0];
                Long count = (Long) result[1];
                map.put(genreName, count);
            }
            et.commit();
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
