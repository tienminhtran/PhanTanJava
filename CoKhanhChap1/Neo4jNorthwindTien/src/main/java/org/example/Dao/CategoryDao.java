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
		Map<String, Object> param = Map.of("id", id);
		try (Session session = driver.session()) {
			Result result = session.run(query, param);
			if (result.hasNext()) {
				Record record = result.next();
				Node node = record.get("n").asNode();
				Category category = new Category(
						node.get("categoryID").asString(),
						node.get("categoryName").asString(),
						node.get("description").asString(),
						node.get("picture").asString()
				);
				return category;
			}
		}
		return null;
	}


	/**
	 * Xóa
	 */
	public void deleteCategory(int ma) {
		String query = "match (c: Category {categoryID: $id}) detach delete c";
		Map<String, Object> param = Map.of("id", ma);
		try (Session session = driver.session()) {
			Result result = session.run(query, param);
			while (result.hasNext()) {
				Record record = result.next();
				Node node = record.get("c").asNode();
				System.out.println(node.get("categoryID").asString());
			}
		}
	}

//

//	 Tìm theo list

//

	/**
	 * Create a new category
	 * @param "_category - Category object
	 * MATCH (p: Person {name: "Jennifer"}) RETURN p
	 */


	public void addCategory(Category category) {
		String query = "CREATE (n:Category {categoryID: $categoryID, categoryName: $categoryName, description: $description})";
		Map<String, Object> pars = AppUtils.getProperties(category);
		try (Session session = driver.session()) {
			Result result = session.run(query, pars);
			while (result.hasNext()) {
				Record record = result.next();
				Node node = record.get("n").asNode();
				System.out.println(node.get("categoryID").asString());
			}
		}
	}
	public void updateCategory(Category category) {
		String query = "MATCH (n:Category {categoryID: $categoryID}) " +
				"SET n.categoryName = $categoryName," +
				"n.description = $description, " +
				"n.picture = $picture";
		Map<String, Object> pars = AppUtils.getProperties(category);
		try(Session session = driver.session()){
			Result result = session.run(query, pars);
			while (result.hasNext()) {
				Record record = result.next();
				Node node = record.get("n").asNode();
				System.out.println(node.get("categoryID").asString());
			}
		}
	}

	public void close () {
		driver.close();
	}
}
