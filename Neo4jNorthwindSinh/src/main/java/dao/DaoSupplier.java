package dao;

import entity.Supplier;
import util.AppUtil;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;
import org.neo4j.driver.types.Node;

import java.util.Map;

public class DaoSupplier {
    private Driver driver;
//    private SessionConfig sessionConfig;
//
    public DaoSupplier() {
        this.driver = AppUtil.getDriver();
//        sessionConfig = SessionConfig
//                .builder()
//                .withDatabase(dbName)
//                .build();
    }


    /**
     * Add new supplier to the database
     * @param supplier
     */
    public Supplier addSupplier(Supplier supplier) {
        String query = "CREATE (s:Supplier {supplierID: $id, companyName: $companyName, contactName: $contactName, contactTitle: $contactTitle, address: $address, city: $city}) return s;";
        Map<String, Object> params = Map.of(
                "id", supplier.getSupplierID(),
                "companyName", supplier.getCompanyName(),
                "contactName", supplier.getContactName(),
                "contactTitle", supplier.getContactTitle(),
                "address", supplier.getAddress(),
                "city", supplier.getCity()
        );

        try (Session session = driver.session()) {
            return session.executeWrite(tx -> {
                Result rs = tx.run(query, params);
                Node node = rs.single().get("s").asNode();
                return AppUtil.nodeToPOJO(node, Supplier.class);

            });
        }

    }

    public Supplier findOneByID(String supplierID) {
        String query = "match (s: Supplier) where s.supplierID = $id return s";
        Map<String, Object> params = Map.of("id", supplierID);

        try (Session session = driver.session()) {
            return session.executeRead(tx -> {
                Result rs = tx.run(query, params);
                if(!rs.hasNext())
                    return null;
                Record record =  rs.single();
                Node node =record.get("s").asNode();
                return AppUtil.nodeToPOJO(node, Supplier.class);
            });
        }
    }


    public void close() {
        if(driver != null)
            driver.close();
    }
}
