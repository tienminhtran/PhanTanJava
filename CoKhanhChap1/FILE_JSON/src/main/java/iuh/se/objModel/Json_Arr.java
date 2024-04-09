/*
 * @(#)Json_Arr.java 1.0  24/2/2024
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

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Json_Arr {
//    public static List<Restaurant> ReadFileArr(String fileName) {
//        List<Restaurant> list = new ArrayList<>();
//        try (JsonReader reader = Json.createReader(new FileReader(fileName))) {
//            {
//                JsonArray ja = reader.readArray();
//                for (JsonValue value : ja) {
//                    Restaurant restaurant = new Restaurant();
//                    restaurant.setRestaurant_id(value.asJsonObject().getString("restaurant_id"));
//                    restaurant.setIs_closed(value.asJsonObject().getBoolean("is_closed"));
//                    restaurant.setName(value.asJsonObject().getString("name"));
//
//
//                    JsonObject jsonObject = value.asJsonObject();
//
//
//                    JsonObject addressJson = jsonObject.getJsonObject("address");
//                    Address add = new Address();
//                    add.setBuilding(addressJson.getString("building"));
//
//                    JsonArray coord = addressJson.getJsonArray("coord");
//                    ArrayList<Double> coordList = new ArrayList<>();
//                    for (JsonValue value1 : coord) {
//                        JsonNumber number = (JsonNumber) value1;
//                        coordList.add(number.doubleValue());
//                    }
//                    add.setCoord(coordList);
//
//
//                    add.setStreet(addressJson.getString("street"));
//                    add.setZipcode(addressJson.getString("zipcode"));
//                    restaurant.setAddress(add);
//
//
//                    restaurant.setBorough(value.asJsonObject().getString("borough"));
//                    restaurant.setCuisine(value.asJsonObject().getString("cuisine"));
//
//
//                    Grades grade = new Grades();
//                    JsonArray grades = jsonObject.getJsonArray("grades");
//                    ArrayList<Grades> gradesList = new ArrayList<>();
//                    for (JsonValue value1 : grades) {
//                        JsonObject gradeJson = (JsonObject) value1;
//
//                        JsonObject dateJson = gradeJson.getJsonObject("date");
//                        Date date = new iuh.se.Date();
//                        date.setDay(dateJson.getInt("day"));
//                        date.setMonth(dateJson.getInt("month"));
//                        date.setYear(dateJson.getInt("year"));
//                        grade.setDate(date);
//                        grade.setGrade(gradeJson.getString("grade"));
//                        grade.setScore(gradeJson.getInt("score"));
//                        gradesList.add(grade);
//                    }
//                    restaurant.setGrades(gradesList);
//
//
//                    list.add(restaurant);
//                }
//
//            }
//
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        return list;
//    }


    public static List<Restaurant> docFlieMangLen(String fileName) {
        List<Restaurant> list = new ArrayList<>();
        Address address = new Address();
        Grades grades = new Grades();
        try (JsonReader reader = Json.createReader(new FileReader(fileName))) {
            JsonArray jsonArray = reader.readArray();
            for (JsonValue value : jsonArray) {
                JsonObject jsonObject = (JsonObject) value;

                Restaurant restaurant = new Restaurant();
                restaurant.setRestaurant_id(jsonObject.getString("restaurant_id"));
                restaurant.setIs_closed(jsonObject.getBoolean("is_closed"));
                restaurant.setName(jsonObject.getString("name"));
                restaurant.setBorough(jsonObject.getString("borough"));
                restaurant.setCuisine(jsonObject.getString("cuisine"));


                JsonObject addressJson = jsonObject.getJsonObject("address");
                address.setBuilding(addressJson.getString("building"));
                address.setStreet(addressJson.getString("street"));
                address.setZipcode(addressJson.getString("zipcode"));

                JsonArray coord = addressJson.getJsonArray("coord");
                ArrayList<Double> coordList = new ArrayList<>();
                for (JsonValue value1 : coord) {
                    JsonNumber number = (JsonNumber) value1; //ép kiểu
                    coordList.add(number.doubleValue());
                }
                address.setCoord(coordList);
                restaurant.setAddress(address);


                JsonArray gradesArray = jsonObject.getJsonArray("grades");
                ArrayList<Grades> gradesList = new ArrayList<>();
                for (JsonValue value1 : gradesArray) {
                    JsonObject gradeJson = (JsonObject) value1;
                    Date date = new Date();
                    date.setDay(gradeJson.getJsonObject("date").getInt("day"));
                    date.setMonth(gradeJson.getJsonObject("date").getInt("month"));
                    date.setYear(gradeJson.getJsonObject("date").getInt("year"));

                    grades.setDate(date);
                    grades.setGrade(gradeJson.getString("grade"));
                    grades.setScore(gradeJson.getInt("score"));
                    gradesList.add(grades);
                }
                restaurant.setGrades(gradesList);



                list.add(restaurant);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void xuat(String fileName, List<Restaurant> restaurants) {
        try (
                JsonWriter writer = Json.createWriter(new FileWriter(fileName))
        ) {
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

            for (Restaurant list : restaurants) {

                JsonObjectBuilder objRes = objectBuilder
                        .add("restaurant_id", list.getRestaurant_id())
                        .add("is_closed", list.isIs_closed())
                        .add("name", list.getName())
                        .add("borough", list.getBorough())
                        .add("cuisine", list.getCuisine());

                JsonObjectBuilder objAddress = objectBuilder
                        .add("building", list.getAddress().getBuilding())
                        .add("coord", list.getAddress().getCoord().toString())
                        .add("street", list.getAddress().getStreet())
                        .add("zipcode", list.getAddress().getZipcode());


                JsonArrayBuilder arrayGrades = Json.createArrayBuilder();
                for (Grades grade : list.getGrades()) {
                    JsonObjectBuilder objDate = objectBuilder
                            .add("Day", grade.getDate().getDay())
                            .add("Month", grade.getDate().getMonth())
                            .add("Year", grade.getDate().getYear());
                    JsonObjectBuilder objGrade = objectBuilder
                            .add("Date", objDate)
                            .add("Grade", grade.getGrade())
                            .add("Score", grade.getScore());
                    arrayGrades.add(objGrade);

                }
                JsonArray jsonArray = arrayGrades.build();
                objRes.add("grades", jsonArray);


                arrayBuilder.add(objRes);  // arrayBuilder.add(ObjectBuilder

            }
            JsonArray jsonArray = arrayBuilder.build();
            writer.writeArray(jsonArray);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void toFileAraaa(String fileName, List<Restaurant> restaurants) {
        try (JsonWriter writer = Json.createWriter(new FileWriter(fileName))) {
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();


            for (Restaurant restaurant : restaurants) {
                // Tạo đối tượng JSON cho thông tin địa chỉ
                JsonObjectBuilder addressJson = objectBuilder
                        .add("building", restaurant.getAddress().getBuilding())
                        .add("coord", restaurant.getAddress().getCoord().toString())
                        .add("street", restaurant.getAddress().getStreet())
                        .add("zipcode", restaurant.getAddress().getZipcode());


                // Tạo đối tượng JSON cho thông tin nhà hàng
                JsonObjectBuilder restaurantJson = objectBuilder
                        .add("restaurant_id", restaurant.getRestaurant_id())
                        .add("is_closed", restaurant.isIs_closed())
                        .add("name", restaurant.getName())
                        .add("address", addressJson)
                        .add("borough", restaurant.getBorough())
                        .add("cuisine", restaurant.getCuisine());

                // Tạo mảng JSON cho thông tin các lần đánh giá
                JsonArrayBuilder gradesArrayBuilder = Json.createArrayBuilder();
                for (Grades grade : restaurant.getGrades()) {
                    JsonObject obDate = objectBuilder
                            .add("day", grade.getDate().getDay())
                            .add("month", grade.getDate().getMonth())
                            .add("year", grade.getDate().getYear())
                            .build();

                    JsonObjectBuilder gradeBuilder = objectBuilder
                            .add("date", obDate)
                            .add("grade", grade.getGrade())
                            .add("score", grade.getScore());
                    gradesArrayBuilder.add(gradeBuilder);

                }

                JsonArray gradesArray = gradesArrayBuilder.build();
                restaurantJson.add("grades", gradesArray);


                // Thêm đối tượng nhà hàng vào mảng JSON
                arrayBuilder.add(restaurantJson);
            }

            JsonArray jsonArray = arrayBuilder.build();
            writer.writeArray(jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<Restaurant> list = docFlieMangLen("FILE_JSON/data/res.json");
        for (Restaurant restaurant : list) {
            System.out.println(restaurant);
        }

        System.out.println("=========================================");
        xuat("FILE_JSON/data/res1.json", list);
        System.out.println("Done!");

    }
}