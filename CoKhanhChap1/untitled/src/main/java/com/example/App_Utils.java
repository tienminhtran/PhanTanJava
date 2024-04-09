package com.example;
import java.net.URI;
import java.util.Map;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.types.Node;

import com.google.gson.Gson;



public class App_Utils {
    private static final Gson GSON = new Gson();
    public static Driver getDriver() {
        URI uri = URI.create("bolt://localhost:7687");
        String user = "neo4j";
        String password = "12345678";
        return org.neo4j.driver.GraphDatabase.driver(uri, org.neo4j.driver.AuthTokens.basic(user, password));
    }
    public static <T> T nodeToPoJO(Node node, Class<T> clazz) {
        Map<String, Object> properties = node.asMap();
        String json = GSON.toJson(properties);
        return GSON.fromJson(json, clazz);
    }
}
