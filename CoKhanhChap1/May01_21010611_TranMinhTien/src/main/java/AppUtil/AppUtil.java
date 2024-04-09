package AppUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.neo4j.driver.Driver;
import org.neo4j.driver.types.Node;

import java.net.URI;
import java.util.Map;

public class AppUtil {

    private static final Gson gson = new Gson();
    public static Driver initDriver() {
        URI uri = URI.create("neo4j://localhost:7687");
        String user = "neo4j";
        String password = "12345678";

        return org.neo4j.driver.GraphDatabase.driver(uri, org.neo4j.driver.AuthTokens.basic(user, password));
    }
    public static <T> T nodeToPOJO (Node node, Class<T> clazz) {
        Map<String, Object> map = node.asMap();
        String json = gson.toJson(map);
        return gson.fromJson(json, clazz);
    }

    public static <T> Map<String, Object> getProperties(T obj) {
        String json = gson.toJson(obj);
        Map<String, Object> map = gson.fromJson(json, new TypeToken<Map<String, Object>>(){});
        return map;
    }
}
