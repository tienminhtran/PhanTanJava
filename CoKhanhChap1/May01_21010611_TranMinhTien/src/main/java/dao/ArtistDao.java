package dao;

import AppUtil.AppUtil;
import entity.Aritst;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;
import org.neo4j.driver.types.Node;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArtistDao {
    private Driver driver;
    private SessionConfig sessionConfig;

    public ArtistDao(String database) {
        driver = AppUtil.initDriver();
        sessionConfig = SessionConfig.builder().withDatabase(database).build();

    }

    public boolean addArtist(Aritst aritst){

        String q = "CREATE (a:Artist) set a.id=$id, a.name=$name, a.birthDate=$birthDate, a.url = $url ";
        Map<String, Object> map = Map.of("id", aritst.getId(), "name", aritst.getName(), "birthDate", aritst.getBirthDate(), "url", aritst.getUrl());
        try(Session session = driver.session(sessionConfig)) {
            session.run(q, map);
            return true;
        }
    }

    public List<Aritst> addListArtist(Aritst aritst){

            String q = "CREATE (a:Artist) set a.id=$id, a.name=$name, a.birthDate=$birthDate, a.url = $url ";
            Map<String, Object> map = Map.of("id", aritst.getId(), "name", aritst.getName(), "birthDate", aritst.getBirthDate(), "url", aritst.getUrl());
            try(Session session = driver.session(sessionConfig)) {
                session.run(q, map);
                List<Aritst> artists = new ArrayList<>();
                artists.add(aritst);
                return artists;
            }
    }

    /**
     * Tiim2m theo ngay
     * @param ngay_sinh
     */
    public List<Aritst> timTheoNgaySinh(LocalDate ngay_sinh){
        String q = "MATCH (a:Artist) \n" +
                "where date(substring(toString(a.birthDate), 0, 10)) = date($NGAY) \n" +
                "RETURN a";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String chuyenchuoi = ngay_sinh.format(formatter);
        List<Aritst> artists = new ArrayList<>();
        try(Session session = driver.session(sessionConfig)) {
            Map<String, Object> map = Map.of("NGAY", ngay_sinh.format(formatter));
            Result result = session.run(q, map);
            while (result.hasNext()){
                Record record = result.next();
                Node node = record.get("a").asNode();
                Aritst aritst = new Aritst(
                        node.get("id").asString(),
                        node.get("name").asString(),
                        ngay_sinh,
                        node.get("url").asString()
                );
                artists.add(aritst);
            }
            return artists;
        }
    }



    public void xoaArtist(String ma){
        String q = "match (a:Artist) WHERE a.id=$id DETACH delete a";
        Map<String, Object> map = Map.of("id", ma);
        try(Session session = driver.session(sessionConfig)) {
            session.run(q, map);
        }
    }
//    private String id;
//    private String name;
//    private LocalDate birthDate;
//    private String url;




    public void close() {
        driver.close();
    }

    public static void main(String[] args) {
        ArtistDao artistDao = new ArtistDao("tien21010611");
        // them 2 data
//        artistDao.addListArtist(new Aritst("1", "Hoa Tau", LocalDate.of(2003, 1, 1), "https://www.google.com"));
//        artistDao.addListArtist(new Aritst("2", "Hoa Tau 2", LocalDate.of(2003, 1, 1), "https://www.google.com"));
//
        // xoa 1 data
//        artistDao.xoaArtist("2");
//        artistDao.close();
        // tim theo ngay
        List<Aritst> artists = artistDao.timTheoNgaySinh(LocalDate.of(2003, 1, 1));
        for (Aritst artist : artists) {
            System.out.println(artist);
        }

    }
}
