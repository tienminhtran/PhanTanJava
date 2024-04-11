/**
 * @ (#) DaoCategory.java      3/5/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package dao;

import entity.Category;
import util.AppUtil;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;

import java.util.Map;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/5/2024
 */
public class DaoCategory {
    private Driver driver;

    public DaoCategory() {
        driver = AppUtil.getDriver();
    }

    /**
     * Create a new category
     * @param category - Category object
     */
    public void addCategory(Category category) {
        String query = "CREATE (n:Category {categoryID: $categoryID, categoryName: $categoryName, description: $description})";

        Map<String, Object> pars = AppUtil.getProperties(category);

        try (Session session = driver.session()) {
            session.executeWrite(tx -> {
                return tx.run(query, pars).consume();
            });
        }
    }

    public Category findOneByID(String id) {
        String query = "match (c: Category) where c.categoryID = $id return c";

        Map<String, Object> params = Map.of("id", id);
        try(Session session = driver.session()) {
            return session.executeRead(tx -> {
                Result rs = tx.run(query, params);
                if(!rs.hasNext()) {
                    return null;
                }
                Record record = rs.next();
                Node node = record.get("c").asNode();
                return AppUtil.nodeToPOJO(node, Category.class);
            });
        }
    }

    public void deleteCategory(String id) {
        String query = "match (c: Category {categoryID: $id}) detach delete c";
        Map<String, Object> param = Map.of("id", id);
        try (Session session = driver.session()) {
            session.executeWrite(tx -> {
                return tx.run(query, param).consume();
            });
        }
    }


    public void close() {
       driver.close();
    }

    public static void main(String[] args) {
        DaoCategory categoryDao = new DaoCategory();
        Category category = new Category("sdfsd", "sdfsd","A124234");
        categoryDao.addCategory(category);
        categoryDao.close();
        System.out.println("Done!");
    }
}
