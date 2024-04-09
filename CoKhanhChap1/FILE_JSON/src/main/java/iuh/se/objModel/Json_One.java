/*
 * @(#)Json_One.java 1.0  24/2/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     24/2/2024
 *@Version:  1.0
 */


package iuh.se.objModel;

import iuh.se.Address;
import iuh.se.Date;
import iuh.se.Grades;
import iuh.se.Restaurant;
import jakarta.json.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Json_One {
    public static Restaurant readFileOne(String fileName) {
        Restaurant restaurant = null;
        Address add = null;
        Grades grade = null;
        try (JsonReader reader = Json.createReader(new FileReader(fileName))) {
            JsonObject jo = reader.readObject();
            if (jo != null) {
                restaurant = new Restaurant();
                restaurant.setRestaurant_id(jo.getString("restaurant_id"));
                restaurant.setIs_closed(jo.getBoolean("is_closed"));
                restaurant.setName(jo.getString("name"));
                restaurant.setBorough(jo.getString("borough"));
                restaurant.setCuisine(jo.getString("cuisine"));


                JsonObject addressJson = jo.getJsonObject("address");
                add = new Address();
                add.setBuilding(addressJson.getString("building"));
                add.setStreet(addressJson.getString("street"));
                add.setZipcode(addressJson.getString("zipcode"));

                JsonArray coord = addressJson.getJsonArray("coord");
                ArrayList<Double> coordList = new ArrayList<>();
                for (JsonValue value : coord) {
                    JsonNumber number = (JsonNumber) value;
                    coordList.add(number.doubleValue());
                }
                add.setCoord(coordList);
                restaurant.setAddress(add);


                JsonArray gradesJson = jo.getJsonArray("grades");
                ArrayList<Grades> gradesList = new ArrayList<>();
                grade = new Grades();
                for (JsonValue value : gradesJson) {
                    JsonObject gradeJson = (JsonObject) value;

                    JsonObject dateJson = gradeJson.getJsonObject("date");
                    Date date = new Date();
                    date.setDay(dateJson.getInt("day"));
                    date.setMonth(dateJson.getInt("month"));
                    date.setYear(dateJson.getInt("year"));
                    grade.setDate(date);


                    grade.setGrade(gradeJson.getString("grade"));
                    grade.setScore(gradeJson.getInt("score"));
                    gradesList.add(grade);
                }
                restaurant.setGrades(gradesList);


            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return restaurant;
    }

    public static void toFileOne(String fileName, Restaurant restaurant) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            JsonObjectBuilder job = Json.createObjectBuilder();

            // Thêm các thuộc tính của nhà hàng vào đối tượng JSON
            JsonObjectBuilder restaurantJson = job
                    .add("restaurant_id", restaurant.getRestaurant_id())
                    .add("is_closed", restaurant.isIs_closed())
                    .add("name", restaurant.getName())
                    .add("borough", restaurant.getBorough())
                    .add("cuisine", restaurant.getCuisine());

            // Thêm thông tin về địa chỉ vào đối tượng JSON
            JsonObjectBuilder addressJson = Json.createObjectBuilder()
                    .add("building", restaurant.getAddress().getBuilding())
                    .add("street", restaurant.getAddress().getStreet())
                    .add("zipcode", restaurant.getAddress().getZipcode())
                    .add("coord", restaurant.getAddress().getCoord().toString());

            restaurantJson.add("address", addressJson);
            // Thêm các đánh giá vào đối tượng JSON
            JsonArrayBuilder gradesArrayBuilder = Json.createArrayBuilder();
            for (Grades grade : restaurant.getGrades()) {
                JsonObjectBuilder gradeJson = Json.createObjectBuilder()
                        .add("date", Json.createObjectBuilder()
                                .add("day", grade.getDate().getDay())
                                .add("month", grade.getDate().getMonth())
                                .add("year", grade.getDate().getYear()))
                        .add("grade", grade.getGrade())
                        .add("score", grade.getScore());
                gradesArrayBuilder.add(gradeJson);
            }

            // Xây dựng mảng JSON chứa các đánh giá
            restaurantJson.add("grades", gradesArrayBuilder);

            // Ghi đối tượng JSON vào tệp
            Json.createWriter(fileWriter).writeObject(restaurantJson.build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
//        Restaurant restaurant = readFileOne("FILE_JSON/data/retaurant.json");
//        System.out.println(restaurant);

        //     toFileOne
        // viet mang add
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurant_id("40358429");
        restaurant.setIs_closed(false);
        restaurant.setName("May May Kitchen");
        restaurant.setBorough("Brooklyn");
        restaurant.setCuisine("Chinese");

        // Thiết lập địa chỉ của nhà hàng
        Address address = new Address();
        address.setBuilding("1269");
        address.setStreet("Sutter Avenue");
        address.setZipcode("11208");
        ArrayList<Double> coord = new ArrayList<>();
        coord.add(-73.871194);
        coord.add(40.6730975);
        address.setCoord(coord);
        restaurant.setAddress(address);

        // Thêm các đánh giá vào nhà hàng
        ArrayList<Grades> grades = new ArrayList<>();
        Grades grade1 = new Grades(new Date(2014, 9, 16), "B", 21);
        Grades grade2 = new Grades(new Date(2013, 8, 28), "A", 7);
        grades.add(grade1);
        grades.add(grade2);
        restaurant.setGrades(grades);

        // Ghi thông tin của nhà hàng vào tệp JSON
        toFileOne("FILE_JSON/data/bbbb.json", restaurant);

        }
    }

