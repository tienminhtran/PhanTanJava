package dao;

import AppUtil.AppUtil;
import entity.Album;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;
import org.neo4j.driver.types.Node;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AlbumDao {
    private Driver driver;
    private SessionConfig sessionConfig;

    public AlbumDao(String database) {
        driver = AppUtil.initDriver();
        sessionConfig = SessionConfig.builder().withDatabase(database).build();

    }

    public void updatePriceOfAlum(String id, double newPrice) {
        String q = "MATCH (a:Album) where a.id=$id set a.price=$price";
        Map<String, Object> map = Map.of("id", id, "price", Double.valueOf(newPrice));
        try (Session session = driver.session(sessionConfig)) {
            session.run(q, map);
        }
    }

    public List<Album> listAlbumByGenre(String genreName) {
        String q = "MATCH (a:Album)-[BELONGS_TO]->(g:Genre) where g.name=$genreName return a";
        List<Album> albums = new ArrayList<>();
        try(Session session = driver.session(sessionConfig)) {
            Result result = session.run(q, Map.of("genreName", genreName));
            while (result.hasNext()) {
                Record record = result.next();
                Node node = record.get("a").asNode();
                Album album = AppUtil.nodeToPOJO(node, Album.class);
                albums.add(album);
            }
            return albums;
        }
    }

    public Map<String, Long> getNumberOfAlbumByGenre(){
        String q = "MATCH (a:Album)-[BELONG_TO]->(g:Genre) return g.name, count(a) as tong ORDER BY g.name";
        Map<String, Long> map = new LinkedHashMap<>();
        try(Session session = driver.session(sessionConfig)) {
            Result result = session.run(q);
            while (result.hasNext()) {
                Record record = result.next();
                String genreName = record.get("g.name").asString();
                long count = record.get("tong").asLong();
                map.put(genreName, count);
            }
            return map;
        }
    }

    //create fulltext index index_name for (a:Album) on each [a.title]


    public List<Album> searchAlbumByName(String title) {
        String q = "call db.index.fulltext.queryNodes(\"index_name\", $title) yield node return node";
        List<Album> albums = new ArrayList<>();
        try(Session session = driver.session(sessionConfig)) {
            Result result = session.run(q,  Map.of("title", title));
            while (result.hasNext()) {
                Record record = result.next();
                Node node = record.get("node").asNode();
                Album album = AppUtil.nodeToPOJO(node, Album.class);
                albums.add(album);

            }
            return albums;
        }
    }
    public void close() {
        driver.close();
    }
}
