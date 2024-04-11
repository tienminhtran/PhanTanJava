/**
 * @ (#) DaoProduct.java      3/9/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package dao;

import entity.Category;
import entity.Product;
import entity.Supplier;
import util.AppUtil;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/9/2024
 */
public class DaoProduct {
    private Driver driver;
    private DaoCategory categoryDao = new DaoCategory();
    private DaoSupplier supplierDao = new DaoSupplier();

    public DaoProduct() {
        driver = AppUtil.getDriver();
    }

    /**
     * Add new product to the database
     * @param product
     */
    public void addProduct(Product product) {
    }

    /**
     * Delete a product from the database
     * @param productID
     */
    public void deleteProduct(String productID) {
    }

    /**
     * Update a product in the database
     * @param product
     */
    public void updateProduct(Product product) {
    }

    /**
     * Find a product by its ID
     * @param productID
     */
    public void findOneByID(String productID) {
    }

    /**
     * Lay tat ca danh sach san pham theo ten danh muc
     * @param categoryName
     * @return
     */
    public List<Product> listProductByCategory(String categoryName) {
        String query = "match (p:Product)-[:PART_OF]->(c:Category {categoryName:$name}) return p";
        Map<String, Object> params = Map.of("name", categoryName);
        try (Session session = driver.session()){
            return session.executeRead(tx -> {
                Result rs = tx.run(query, params);
                if(!rs.hasNext()) {
                    return null;
                }
                return rs.stream()
                        .map(record -> {
                            Node node = record.get("p").asNode();
//                            return AppUtil.nodeToPOJO(node, Product.class);
                            Category category = categoryDao.findOneByID(node.get("categoryID").asString());
                            Supplier supplier = supplierDao.findOneByID(node.get("supplierID").asString());
                            return new Product(node.get("productID").asString(),
                                    supplier,
                                    category,
                                    node.get("productName").asString(),
                                    node.get("quantityPerUnit").asString(),
                                    node.get("unitPrice").asDouble(),
                                    node.get("unitsInStock").asInt(),
                                    node.get("unitsOnOrder").asInt(),
                                    node.get("reorderLevel").asInt()
                            );
                        }).toList();
            });
        }

    }

    /**
     * Lay tat ca san pham co gia tri tien cao nhat
     * @return
     */
    public List<Product> listProductMaxPrice() {
        String query = "match (p:Product) \n" +
                "with max(p.unitPrice) as maxPrice\n" +
                "match (p:Product) \n" +
                "where p.unitPrice = maxPrice\n" +
                "return p";
        try (Session session = driver.session()){
            return session.executeRead(tx -> {
                Result rs = tx.run(query);
                if(!rs.hasNext()) {
                    return null;
                }
                return rs.stream()
                        .map(record -> {
                            Node node = record.get("p").asNode();
//                            return AppUtil.nodeToPOJO(node, Product.class);
                            Category category = categoryDao.findOneByID(node.get("categoryID").asString());
                            Supplier supplier = supplierDao.findOneByID(node.get("supplierID").asString());
                            return new Product(node.get("productID").asString(),
                                    supplier,
                                    category,
                                    node.get("productName").asString(),
                                    node.get("quantityPerUnit").asString(),
                                    node.get("unitPrice").asDouble(),
                                    node.get("unitsInStock").asInt(),
                                    node.get("unitsOnOrder").asInt(),
                                    node.get("reorderLevel").asInt()
                            );
                        }).toList();
            });
        }

    }


    /**
     * Tinh tong so luong cua tung san pham da ban ra
     * @return Map<String, Integer>
     */
    public Map<String, Integer> getTotalProduct(){
        String query = "match (p:Product)<-[:ORDERS]-(o:Order) \n" +
                "return p.productName, sum(p.unitsOnOrder) as totalProduct";
        try (Session session = driver.session()) {
            return session.executeRead(tx -> {
                Result rs = tx.run(query);
                if(!rs.hasNext()) {
                    return null;
                }
                Map<String, Integer> map = new HashMap<>();
                rs.stream()
                        .forEach(record -> {
                            String productName = record.get("p.productName").asString();
                            int totalProduct = record.get("totalProduct").asInt();
                            map.put(productName, totalProduct);
                        });
                return map;
            });
        }
    }


    /**
     * Tim kiem san pham theo ten
     * @param name
     * @return
     */
    public List<Product> findProductByName(String name) {
        String query = "match (p:Product)\n" +
                "where p.productName CONTAINS $name \n" +
                "return p;";
        Map<String, Object> params = Map.of("name", name);

        try(Session session = driver.session()) {
            return session.executeRead(tx -> {
               Result rs = tx.run(query, params);
               if(!rs.hasNext()) {
                   return null;
               }
               return rs.stream()
                       .map(record -> {
                           Node node = record.get("p").asNode();
                           Category category = categoryDao.findOneByID(node.get("categoryID").asString());
                           Supplier supplier = supplierDao.findOneByID(node.get("supplierID").asString());
                           return new Product(node.get("productID").asString(),
                                   supplier,
                                   category,
                                   node.get("productName").asString(),
                                   node.get("quantityPerUnit").asString(),
                                   node.get("unitPrice").asDouble(),
                                   node.get("unitsInStock").asInt(),
                                   node.get("unitsOnOrder").asInt(),
                                   node.get("reorderLevel").asInt()
                           );
                       }).toList();




            });
        }

    }

    public void close() {
        driver.close();
    }

    public static void main(String[] args) {
        DaoProduct dao = new DaoProduct();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vui long nhap ten san pham can tim: ");
        String name = scanner.nextLine();
        List<Product> products = dao.findProductByName(name);
        products.forEach(System.out::println);

//        System.out.println(product);
        dao.close();
    }
}

