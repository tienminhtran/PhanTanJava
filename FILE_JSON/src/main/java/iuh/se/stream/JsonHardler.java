/*
 * @(#)JsonHardler.java 1.0  23/2/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     23/2/2024
 *@Version:  1.0
 */


package iuh.se.stream;

import iuh.se.Address;
import iuh.se.Date;
import iuh.se.Grades;
import iuh.se.Restaurant;
import jakarta.json.*;
import jakarta.json.stream.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonHardler {
    public static Restaurant readFile(String fileName) {
        Restaurant restaurant = null;
        Address address = null;
        List<Grades> grades = new ArrayList<>();
        List<Double> coord = new ArrayList<>();

        String keyName = "";
        try (JsonParser parser = Json.createParser(new FileReader(fileName))) {
            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                switch (event) {
                    case KEY_NAME:
                        keyName = parser.getString();
                        break;
                    case VALUE_STRING:
                        if (keyName.equals("restaurant_id")) {
                            restaurant = new Restaurant();
                            restaurant.setRestaurant_id(parser.getString());
                        } else if (keyName.equals("name")) {
                            restaurant.setName(parser.getString());
                        } else if (keyName.equals("borough")) {
                            restaurant.setBorough(parser.getString());
                        } else if (keyName.equals("cuisine")) {
                            restaurant.setCuisine(parser.getString());
                        }
                        break;
                    case VALUE_TRUE:
                        if (keyName.equals("is_closed") && restaurant != null) {
                            restaurant.setIs_closed(true);
                        }
                        break;
                    case VALUE_FALSE:
                        if (keyName.equals("is_closed") && restaurant != null) {
                            restaurant.setIs_closed(false);
                        }
                        break;
                    case START_ARRAY:
                        if (keyName.equals("grades")) {
                            grades = new ArrayList<>();
                            JsonArray ja = parser.getArray();
                            for (JsonValue jv : ja) {
                                JsonObject joGr = (JsonObject) jv;
                                JsonObject joDate = joGr.getJsonObject("date");
                                Date date = new Date(joDate.getInt("day"), joDate.getInt("month"), joDate.getInt("year"));
                                grades.add(new Grades(date, joGr.getString("grade"), joGr.getInt("score")));
                            }
                            restaurant.setGrades(grades);
                        } else if (keyName.equals("coord")) {
                            while (parser.hasNext() && parser.next() != JsonParser.Event.END_ARRAY) {
                                if (parser.hasNext() && parser.next() == JsonParser.Event.VALUE_NUMBER) {
                                    coord.add(parser.getBigDecimal().doubleValue());
                                }
                            }
                        }
                        break;
                    case START_OBJECT:
                        if (keyName.equals("address")) {
                            address = new Address();
                        }
                        break;
                    case END_ARRAY:
                        if (keyName.equals("coord")) {
                            address.setCoord(coord);
                        }
                        break;
                    case END_OBJECT:
                        if (keyName.equals("address")) {
                            restaurant.setAddress(address);
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return restaurant;
    }


    /*
    public static List<Restaurant> readFileArr(String fileName) {

        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = null;
        Address address = null;
        List<Grades> grades = new ArrayList<>();
        List<Double> coord = new ArrayList<>();
        String keyName = "";
        try (JsonParser parser = Json.createParser(new FileReader(fileName))) {
            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                switch (event) {
                    case KEY_NAME:
                        keyName = parser.getString();
                        break;
                    case VALUE_STRING:
                        if (keyName.equals("restaurant_id")) {
                            restaurant = new Restaurant();
                            restaurant.setRestaurant_id(parser.getString());
                        } else if (keyName.equals("name")) {
                            restaurant.setName(parser.getString());
                        } else if (keyName.equals("borough")) {
                            restaurant.setBorough(parser.getString());
                        } else if (keyName.equals("cuisine")) {
                            restaurant.setCuisine(parser.getString());
                        }
                        break;
                    case VALUE_TRUE:
                        if (keyName.equals("is_closed") && restaurant != null) {
                            restaurant.setIs_closed(true);
                        }
                        break;
                    case VALUE_FALSE:
                        if (keyName.equals("is_closed") && restaurant != null) {
                            restaurant.setIs_closed(false);
                        }
                        break;
                    case START_ARRAY:
                        if (keyName.equals("grades")) {
                            grades = new ArrayList<>();
                            JsonArray ja = parser.getArray();
                            for (JsonValue jv : ja) {
                                JsonObject joGr = (JsonObject) jv;
                                JsonObject joDate = joGr.getJsonObject("date");
                                Date date = new Date(joDate.getInt("day"), joDate.getInt("month"), joDate.getInt("year"));
                                grades.add(new Grades(date, joGr.getString("grade"), joGr.getInt("score")));
                            }
                            restaurant.setGrades(grades);
                        } else if (keyName.equals("coord")) {
                            while (parser.hasNext() && parser.next() != JsonParser.Event.END_ARRAY) {
                                if (parser.hasNext() && parser.next() == JsonParser.Event.VALUE_NUMBER) {
                                    coord.add(parser.getBigDecimal().doubleValue());
                                }
                            }
                        } else if (keyName.equals("restaurants")) {
                            restaurants = new ArrayList<>();
                        }
                        break;

                    case START_OBJECT:
                        if(keyName.equals("restaurant_id")) {
                            restaurant = new Restaurant();
                            restaurant.setRestaurant_id(parser.getString());
                        }
                        break;

                    case END_ARRAY:
                        if (keyName.equals("coord")) {
                            address.setCoord(coord);
                        }
                        break;
                    case END_OBJECT:
                        if (keyName.equals("address")) {
                            restaurant.setAddress(address);
                        } else if (keyName.equals("restaurants")) {
                            restaurants.add(restaurant);
                        }
                        break;
                    default:
                        break;
                }
            }
            return restaurants;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

     */

    public static List<Restaurant> readFileArr(String fileName) {
        List<Restaurant> restaurants = new ArrayList<>();
        try (JsonReader reader = Json.createReader(new FileReader(fileName))) {
            JsonParser parser = Json.createParser(new FileReader(fileName));
            Restaurant restaurant = null;
            Address address = null;
            List<Grades> grades = null;
            List<Double> coord = null;
            String keyName = "";

            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                switch (event) {
                    case KEY_NAME:
                        keyName = parser.getString();
                        break;
                    case VALUE_STRING:
                        if (keyName.equals("restaurant_id")) {
                            restaurant = new Restaurant();
                            restaurant.setRestaurant_id(parser.getString());
                        } else if (keyName.equals("name")) {
                            restaurant.setName(parser.getString());
                        } else if (keyName.equals("borough")) {
                            restaurant.setBorough(parser.getString());
                        } else if (keyName.equals("cuisine")) {
                            restaurant.setCuisine(parser.getString());
                        }
                        break;
                    case VALUE_TRUE:
                        if (keyName.equals("is_closed") && restaurant != null) {
                            restaurant.setIs_closed(true);
                        }
                        break;
                    case VALUE_FALSE:
                        if (keyName.equals("is_closed") && restaurant != null) {
                            restaurant.setIs_closed(false);
                        }
                        break;
                    case START_ARRAY:
                        if (keyName.equals("grades")) {
                            grades = new ArrayList<>();
                        } else if (keyName.equals("coord")) {
                            coord = new ArrayList<>();
                        }
                        break;
                    case VALUE_NUMBER:
                        if (keyName.equals("coord") && coord != null) {
                            coord.add(parser.getBigDecimal().doubleValue());
                        }
                        break;
                    case START_OBJECT:
                        if (keyName.equals("address")) {
                            address = new Address();
                        }
                        break;
                    case END_OBJECT:
                        if (keyName.equals("address")) {
                            restaurant.setAddress(address);
                        } else if (keyName.equals("grades")) {
                            restaurant.setGrades(grades);
                        } else if (keyName.equals("restaurants")) {
                            restaurants.add(restaurant);
                        }
                        break;
                    case END_ARRAY:
                        if (keyName.equals("coord") && address != null) {
                            address.setCoord(coord);
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return restaurants;
    }
    public static void main(String[] args) {
//        Restaurant p = JsonHardler.readFile("FILE_JSON/data/retaurant.json");
//            System.out.println(p);

        List<Restaurant> list = JsonHardler.readFileArr("FILE_JSON/data/res.json");
        for (Restaurant restaurant : list) {
            System.out.println(restaurant);
        }

    }
}