package org.example.Dao;

import org.example.AppUtils.AppUtils;
import org.example.entity.Category;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;

import java.util.Map;


public class CategoryDao {
	private Driver driver;
	
	public CategoryDao() {
		driver = AppUtils.initDriver();
	}
	
	public Category findOne(String id) {
		String query = "MATCH (n:Category) WHERE n.categoryID = $id RETURN n";
		Map<String, Object> map = Map.of("id", id);
		
		try(Session session = driver.session()){
			return session.executeRead(tx -> {
				Result result = tx.run(query, map);
				if(!result.hasNext())
					return null;
				Record record = result.next();
				Node node = record.get("n").asNode();
//				String name = node.get("categoryName").asString();
//				String dest = node.get("description").asString();
//				return new Category(id, name, dest);
				Category c = AppUtils.convert(node, Category.class);
				return c;
				
			});
		}
	}
//

//	 Tìm theo list

//

	/**
	 * Create a new category
	 * @param "_category - Category object
	 */

	public void addCategory(Category category) {
		String query = "CREATE (n:Category {categoryID: $categoryID, categoryName: $categoryName, description: $description})";
		Map<String, Object> pars = AppUtils.getProperties(category);
		try (Session session = driver.session()) {
			session.executeWrite(tx -> {
				return tx.run(query, pars).consume();
			});
		}
	}
	public void updateCategory(Category category) {
		String query = "MATCH (n:Category {categoryID: $categoryID}) " +
				"SET n.categoryName = $categoryName," +
				"n.description = $description, " +
				"n.picture = $picture";
		Map<String, Object> pars = AppUtils.getProperties(category);
		try(Session session = driver.session()){
			session.executeWrite(tx -> {
				return tx.run(query, pars).consume();
			});
		}
	}

	public void close () {
		driver.close();
	}
}
