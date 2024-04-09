package util;

import java.net.URI;
import java.util.Map;

import com.google.gson.Gson;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.types.Node;

public class AppUtils {
	private static final Gson GSON = new Gson();

	public static Driver initDriver() {
		
		URI uri = URI.create("neo4j://localhost:7687");
		String user = "neo4j";
		
		String password = "12345678";
		return GraphDatabase.driver(uri , AuthTokens.basic(user , password ));
		
	}
	public static <T> T convert(Node node, Class<T> clazz) {
		Map<String, Object> properties = node.asMap();
//		System.out.println(properties);

		String json = GSON.toJson(properties);

//		System.out.println(json);

		return GSON.fromJson(json, clazz);
	}


}
