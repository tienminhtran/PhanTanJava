/*
 * @(#)deMo.java 1.0  25/2/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     25/2/2024
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
import java.util.ArrayList;
import java.util.List;

public class deMo {

    public static List<Restaurant> doc(String fileName) {

        List<Restaurant> list = new ArrayList<>();
        Address address = new Address();
        Grades grades = new Grades();
        try (JsonReader reader = Json.createReader(new FileReader(fileName))) {
            JsonArray jsonArray = reader.readArray();
            for (JsonValue value : jsonArray) {
                JsonObject object = (JsonObject) value;
                Restaurant restaurant = new Restaurant();
                restaurant.setRestaurant_id(object.getString("restaurant_id"));
                restaurant.setIs_closed(object.getBoolean("is_closed"));
                restaurant.setName(object.getString("name"));
                restaurant.setBorough(object.getString("borough"));
                restaurant.setCuisine(object.getString("cuisine"));


                JsonObject addressObject = object.getJsonObject("address");
                address.setBuilding(addressObject.getString("building"));
                address.setStreet(addressObject.getString("street"));
                address.setZipcode(addressObject.getString("zipcode"));

                List<Double> coord = new ArrayList<>();
                JsonArray coordArray = addressObject.getJsonArray("coord");
                for (JsonValue coordValue : coordArray) {
//                    JsonNumber coordNumber = (JsonNumber) coordValue;
//                    coord.add(coordNumber.doubleValue());
                    coord.add(Double.parseDouble(coordValue.toString()));
                }
                address.setCoord(coord);

                JsonArray gradesArray = object.getJsonArray("grades");
                List<Grades> gradesList = new ArrayList<>();
                for (JsonValue gradeValue : gradesArray) {

                    JsonObject dateObject = ((JsonObject) gradeValue).getJsonObject("date");
                    Date date = new Date();
                    date.setDay(dateObject.getInt("day"));
                    date.setMonth(dateObject.getInt("month"));
                    date.setYear(dateObject.getInt("year"));

                    JsonObject gradeObject = (JsonObject) gradeValue;
                    grades.setDate(date);
                    grades.setGrade(gradeObject.getString("grade"));
                    grades.setScore(gradeObject.getInt("score"));
                    gradesList.add(grades);
                }
                restaurant.setGrades(gradesList);

                restaurant.setAddress(address);

                list.add(restaurant); // list -> res -> add
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void ghi(String fileName, List<Restaurant> list) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        try (JsonWriter writer = Json.createWriter(new FileWriter(fileName))) {
            for (Restaurant restaurant : list) {
                objectBuilder.add("restaurant_id", restaurant.getRestaurant_id());
                objectBuilder.add("is_closed", restaurant.isIs_closed());
                objectBuilder.add("name", restaurant.getName());
                objectBuilder.add("borough", restaurant.getBorough());
                objectBuilder.add("cuisine", restaurant.getCuisine());


                JsonArrayBuilder coordArrayBuilder = Json.createArrayBuilder();
                for(Double coord : restaurant.getAddress().getCoord()){
                    coordArrayBuilder.add(coord);
                }
                JsonObjectBuilder addressObjectBuilder = Json.createObjectBuilder();
                addressObjectBuilder.add("building", restaurant.getAddress().getBuilding());
                addressObjectBuilder.add("street", restaurant.getAddress().getStreet());
                addressObjectBuilder.add("zipcode", restaurant.getAddress().getZipcode());
                addressObjectBuilder.add("coord", coordArrayBuilder.build());


                objectBuilder.add("address", addressObjectBuilder.build());

                JsonArrayBuilder gradesArrayBuilder = Json.createArrayBuilder();
                for(Grades grade : restaurant.getGrades()){
                    JsonObjectBuilder dateObjectBuilder = Json.createObjectBuilder();
                    dateObjectBuilder.add("day", grade.getDate().getDay());
                    dateObjectBuilder.add("month", grade.getDate().getMonth());
                    dateObjectBuilder.add("year", grade.getDate().getYear());




                    JsonObjectBuilder gradeObjectBuilder = Json.createObjectBuilder();
                    gradeObjectBuilder.add("date", dateObjectBuilder.build());
                    gradeObjectBuilder.add("grade", grade.getGrade());
                    gradeObjectBuilder.add("score", grade.getScore());
                    gradesArrayBuilder.add(gradeObjectBuilder);
                }
                objectBuilder.add("grades", gradesArrayBuilder.build());





                arrayBuilder.add(objectBuilder);
            }
            writer.writeArray(arrayBuilder.build());
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<Restaurant> list = doc("FILE_JSON/data/res.json");
        for (Restaurant restaurant : list) {
            System.out.println(restaurant);
        }

        ghi("FILE_JSON/data/resttt.json", list);


    }
}
