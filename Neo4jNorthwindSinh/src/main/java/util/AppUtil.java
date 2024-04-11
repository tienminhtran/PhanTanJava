package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Supplier;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.types.Node;

import java.net.URI;
import java.util.Map;

public class AppUtil<T> {
    public static final Gson GSON = new Gson();
    public static Driver getDriver() {
//        String uri = "neo4j://localhost:7687";
//        String user = "neo4j";
//        String password = "12345678";
//        return GraphDatabase.driver(uri, AuthTokens.basic(user, password));
        return GraphDatabase.driver("neo4j://localhost:7687",
                AuthTokens.basic("neo4j", "123456789"));
    }

    /**
     * Lay nhung thuoc tinh cua mot doi tuong ung voi properties cua node do
     * @param node
     * @param clazz
     * @return
     * @param <T>
     */
    public static <T> T nodeToPOJO(Node node, Class<T> clazz) { //Generic method

        Map<String, Object> properties = node.asMap(); // HashMap<String, Object>
//		System.out.println(properties);

        String json = GSON.toJson(properties);
//		System.out.println(json);

        T obj = GSON.fromJson(json, clazz);

        return obj;
    }

    public static <T> Map<String, Object> getProperties(T t) {

        String json = GSON.toJson(t);
//		System.out.println(json);

//		Map<String, Object> map = GSON.fromJson(json, Map.class);
        Map<String, Object> map = GSON.fromJson(json,  new TypeToken<Map<String, Object>>(){});
//		System.out.println(map);

        return map;
    }
}
