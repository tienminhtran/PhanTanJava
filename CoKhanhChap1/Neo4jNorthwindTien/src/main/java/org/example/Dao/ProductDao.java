package org.example.Dao;

import org.example.AppUtils.AppUtils;
import org.example.entity.Category;
import org.example.entity.Product;
import org.example.entity.Supplier;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductDao {
    private Driver driver;

    public ProductDao() {
        driver = AppUtils.initDriver();
    }

    public List<Product> findListProduct_Supplier(String ten) {
        String query = "MATCH (S:Supplier) -[SUPPLIES]-> (p:Product) where S.companyName = $companyName return p";
        Map<String, Object> map = Map.of("companyName", ten);
        List<Product> productList = new ArrayList<>();
        try (Session session = driver.session()) {
            Result result = session.run(query, map);
            while (result.hasNext()) {
                Record record = result.next();
                Node node = record.get("p").asNode();

                Product product = new Product(
                        node.get("productID").asString(),
                        node.get("productName").asString(),
                        new Supplier(node.get("supplierID").asString()),
                        null,
                        node.get("quantityPerUnit").asString(),
                        node.get("unitPrice").asDouble());
                productList.add(product);
            }
        }
        return productList;
    }


    /**
     * Tìm sản phẩm có giá cao nhất
     * step 1: MATCH (P:Product) WITH MAX(P.unitPrice) AS maximun RETURN  maximun -> 263.5
     * MATCH (P:Product) where P.unitPrice = 263.5 return P
     */
    public void findProductByPriceMax() {
        String query1 = "MATCH (P:Product) WITH MAX(P.unitPrice) AS maximun RETURN  maximun";
        double temp = 0;
        try (Session session = driver.session()) {
            Result result = session.run(query1);
            if (result.hasNext()) {
                Record record = result.next();
                temp = record.get("maximun").asDouble();
            }
        }

        String query = "MATCH (P:Product) where P.unitPrice = $unitPrice return P";
        Map<String, Object> map = Map.of("unitPrice", temp);
        List<Product> productList = new ArrayList<>();
        try (Session session = driver.session()) {
            Result result = session.run(query, map);
            while (result.hasNext()) {
                Record record = result.next();
                Node node = record.get("P").asNode();
                Product product = new Product(
                        node.get("productID").asString(),
                        node.get("productName").asString(),
                        new Supplier(node.get("supplierID").asString()),
                        null,
                        node.get("quantityPerUnit").asString(),
                        node.get("unitPrice").asDouble());
                productList.add(product);
            }
        }
        productList.forEach(System.out::println);
    }




    /**
     * Chu yeu de sua unitPrice = cao nhat 263.5
     * @param product
     */

    public void upDate_Pro(Product product) {
        String query = "MATCH (n:Product) where n.productID = $productID " +
                "SET n.unitPrice = $unitPrice";
        Map<String, Object> pars = AppUtils.getProperties(product);
        try(Session session = driver.session()){
            session.executeWrite(tx -> {
                return tx.run(query, pars).consume();
            });
        }
    }
    public void close() {
        driver.close();
    }

    public static void main(String[] args) {
//        findProductByPriceMax
        ProductDao productDao = new ProductDao();
productDao.findProductByPriceMax();
        productDao.close();


    }
}
