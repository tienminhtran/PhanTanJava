/**
 * @ (#) DaoOrder.java      3/11/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package dao;

import util.AppUtil;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/11/2024
 */
public class DaoOrder {
    private Driver driver;

    public DaoOrder() {
        driver = AppUtil.getDriver();
    }


    /**
     * Tinh tong tien cua don hang khi biet ma don hang
     *
     * @return int
     */
    public double sumPriceOrderByID(String orderID) {
        String query = "match (o:Order)-[:ORDERS]->(p:Product)\n" +
                "where o.orderID = $id\n" +
                "return sum(p.unitPrice * p.unitsOnOrder) as sumPrice";
        Map<String, Object> params = Map.of("id", orderID);
        try(Session session = driver.session()) {
            return session.executeRead(tx -> {
                Result rs = tx.run(query, params);
                if(!rs.hasNext()) {
                    return 0.0;
                }
                Record record = rs.single();
                double sumPrice = record.get("sumPrice").asNumber().doubleValue();
                return sumPrice;
            });
        }
    }

    /**
     * Tinh tong tien cua tat ca don hang vao ngay nao do
     * @param dateSearch
     * @return int
     */
    public double sumPricerOrderByDateOrder(LocalDate dateSearch) {
        String query = "match (o:Order)-[:ORDERS]->(p:Product)\n" +
                "where o.orderDate contains $date\n" +
                "return sum(p.unitPrice * p.unitsOnOrder) as sumPrice";
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateFormat = sdf.format(dateSearch);

        Map<String, Object> params = Map.of("date", dateFormat);

        try(Session session = driver.session()) {
            return session.executeRead(tx -> {
                Result rs = tx.run(query, params);
                if(!rs.hasNext()) {
                    return 0.0;
                }
                Record record = rs.next();
                return record.get("sumPrice").asNumber().doubleValue();
            });
        }

    }

    public void close() {
        driver.close();
    }
}
