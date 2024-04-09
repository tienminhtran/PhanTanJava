package org.example.AppUtils;

import com.google.gson.Gson;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.types.Node;
import com.google.gson.reflect.TypeToken;

import java.net.URI;
import java.util.Map;

public class AppUtils {
    private static final Gson GSON = new Gson();

    public static Driver initDriver() {

        URI uri = URI.create("neo4j://localhost:7687");
        String user = "neo4j";
        String password = "12345678";
        return GraphDatabase.driver(uri , AuthTokens.basic(user , password ));

    }

    /**
     * Chuyển đổi một đối tượng Node thành một đối tượng POJO:
     * @param node
     * @param clazz
     * @return
     * @param <T>
     */
    public static <T> T nodeToPOJO(Node node, Class<T> clazz) {
        Map<String, Object> properties = node.asMap();
//		System.out.println(properties);
        String json = GSON.toJson(properties);
//		System.out.println(json);
        return GSON.fromJson(json, clazz);
    }

    /**
     * Chuyển đổi một đối tượng POJO thành một Map<String, Object>:
     * @param t
     * @return
     * @param <T>
     */
    public static <T> Map<String, Object> getProperties(T t) {

        String json = GSON.toJson(t);
//		System.out.println(json);

//		Map<String, Object> map = GSON.fromJson(json, Map.class);
        Map<String, Object> map = GSON.fromJson(json,  new TypeToken<Map<String, Object>>(){});
//		System.out.println(map);

        return map;
    }

}
