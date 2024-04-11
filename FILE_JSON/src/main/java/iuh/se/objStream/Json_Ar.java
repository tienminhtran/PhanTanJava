/*
 * @(#)Json_Arr.java 1.0  25/2/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     25/2/2024
 *@Version:  1.0
 */


package iuh.se.objStream;

import iuh.se.Address;
import iuh.se.Date;
import iuh.se.Grades;
import iuh.se.Restaurant;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Json_Arr {

    public static List<Restaurant> gg(String fileName) {
        List<Restaurant> listRes = new ArrayList<>();
        try (JsonParser parser = Json.createParser(new FileReader(fileName))) {
            Restaurant res = null;
            Address add = null;
            Grades grades = null;
            Date date = null;
            String keyName = "";
            List<Double> coordList = new ArrayList<>();
            List<Grades> gradesList = new ArrayList<>();
            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                switch (event) {
                    case START_ARRAY: {
                        if (keyName.equals("coord")) {
                            JsonArray ja = parser.getArray();
                            for (int i = 0; i < ja.size(); i++) {
                                coordList.add(ja.getJsonNumber(i).doubleValue());
                            }
                            add.setCoord(coordList);
                        } else if (keyName.equals("grades")) {
                            gradesList = new ArrayList<>();
                        } else {
                            listRes = new ArrayList<>();
                        }
                        break;
                    }
                    case START_OBJECT: {
                        if (keyName.equals("address")) {
                            add = new Address();
                        } else if ((keyName.equals("grades") || keyName.equals("score")) && res.getGrades() == null) {
                            grades = new Grades();
                        } else if (keyName.equals("date")) {
                            date = new Date();
                        } else {
                            res = new Restaurant();
                        }
                        break;
                    }
                    case KEY_NAME: {
                        keyName = parser.getString();
                        break;
                    }
                    case VALUE_STRING: {
                        if (keyName.equals("restaurant_id")) {
                            res.setRestaurant_id(parser.getString());
                        } else if (keyName.equals("street")) {
                            add.setStreet(parser.getString());
                        } else if (keyName.equals("zipcode")) {
                            add.setZipcode(parser.getString());
                        } else if (keyName.equals("borough")) {
                            res.setBorough(parser.getString());
                        } else if (keyName.equals("cuisine")) {
                            res.setCuisine(parser.getString());
                        } else if (keyName.equals("name")) {
                            res.setName(parser.getString());
                        } else if (keyName.equals("grade")) {
                            grades.setGrade(parser.getString());
                        } else if (keyName.equals("building")) {
                            add.setBuilding(parser.getString());
                        }
                        break;
                    }

                    case VALUE_TRUE:
                        res.setIs_closed(true);
                        break;

                    case VALUE_FALSE:
                        res.setIs_closed(false);
                        break;

                    case VALUE_NUMBER: {
                        if (keyName.equals("score")) {
                            grades.setScore(parser.getInt());
                        } else if (keyName.equals("year")) {
                            date.setYear(parser.getInt());
                        } else if (keyName.equals("month")) {
                            date.setMonth(parser.getInt());
                        } else if (keyName.equals("day")) {
                            date.setDay(parser.getInt());
                        }
                        break;
                    }
                    case END_OBJECT: {
                        if (keyName.equals("zipcode")) {
                            res.setAddress(add);
                        } else if ((keyName.equals("grades") || keyName.equals("score")) && res.getGrades() == null) {
                            gradesList.add(grades);
                        } else if (keyName.equals("day")) {
                            grades.setDate(date);
                        } else {
                            listRes.add(res);
                        }
                        break;
                    }
                    case END_ARRAY: {
                        if (res.getGrades() == null) {
                            res.setGrades(gradesList);
                        } else {
                            return listRes;
                        }
                        break;
                    }
                }


            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        return listRes;

    }

    public static Restaurant read(String fileName) {
        Restaurant res = null;
        Address address = null;
        Grades grades = null;
//        List<Restaurant> listRes = null;
        List<Grades> listGrades = null;
        List<Double> coord = null;
        Date date = null;
        String keyName = "";

        try (JsonParser parser = Json.createParser(new FileReader(fileName))) {
            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                switch (event) {
                    case START_OBJECT:
                        if (keyName.equals("address")) {
                            address = new Address();
                            JsonObject joAdd = parser.getObject();
                            address.setBuilding(joAdd.getString("building"));
                            address.setStreet(joAdd.getString("street"));
                            address.setZipcode(joAdd.getString("zipcode"));

                            coord = new ArrayList<>();
                            JsonArray ja = joAdd.getJsonArray("coord");
                            for (int i = 0; i < ja.size(); i++) {
                                coord.add(ja.getJsonNumber(i).doubleValue());
                            }
                            address.setCoord(coord);
                        } else if (keyName.equals("grades")) {
                            grades = new Grades();
                            listGrades = new ArrayList<>();
                        } else {
                            res = new Restaurant();
                        }

                    case START_ARRAY:
                        if (keyName.equals("grades")) {
                            listGrades = new ArrayList<>();
                            JsonArray ja = parser.getArray();
                            for (int i = 0; i < ja.size(); i++) {
                                grades = new Grades();
                                JsonObject joGrade = ja.getJsonObject(i);
                                grades.setGrade(joGrade.getString("grade"));
                                grades.setScore(joGrade.getInt("score"));

                                date = new Date();
                                JsonObject joDate = joGrade.getJsonObject("date");
                                date.setYear(joDate.getInt("year"));
                                date.setMonth(joDate.getInt("month"));
                                date.setDay(joDate.getInt("day"));
                                grades.setDate(date);
                                listGrades.add(grades);
                            }
                        }
                        break;

                    case KEY_NAME:
                        keyName = parser.getString();
                        break;

                    case VALUE_STRING:
                        if (keyName.equals("restaurant_id")) {
                            res.setRestaurant_id(parser.getString());
                        } else if (keyName.equals("name")) {
                            res.setName(parser.getString());
                        } else if (keyName.equals("borough")) {
                            res.setBorough(parser.getString());
                        } else if (keyName.equals("cuisine")) {
                            res.setCuisine(parser.getString());
                        } else if (keyName.equals("grade")) {
                            grades.setGrade(parser.getString());
                        }
                        break;

                    case VALUE_NUMBER:
                        if (keyName.equals("score")) {
                            grades.setScore(parser.getInt());
                        } else if (keyName.equals("year")) {
                            date.setYear(parser.getInt());
                        } else if (keyName.equals("month")) {
                            date.setMonth(parser.getInt());
                        } else if (keyName.equals("day")) {
                            date.setDay(parser.getInt());
                        }
                        break;

                    case VALUE_TRUE:
                        res.setIs_closed(true);
                        break;
                    case VALUE_FALSE:
                        res.setIs_closed(false);

                    case END_OBJECT:
                        if (keyName.equals("address")) {
                            res.setAddress(address);
                        } else if (keyName.equals("grades")) {
                            res.setGrades(listGrades);
                        }
                        break;

                    case END_ARRAY:
                        break;


                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }


    public static List<Restaurant> readArr(String fileName) {
        List<Restaurant> listRes = null;
        Restaurant res = null;
        Address address = null;
        Grades grades = null;
        List<Grades> listGrades = null;
        List<Double> coord = null;
        Date date = null;
        String keyName = "";

        try (JsonParser parser = Json.createParser(new FileReader(fileName))) {
            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                switch (event) {
                    case START_ARRAY:
                        if (keyName.equals("grades")) {
                            listGrades = new ArrayList<>();
                            JsonArray ja = parser.getArray();
                            for (int i = 0; i < ja.size(); i++) {
                                grades = new Grades();
                                JsonObject joGrade = ja.getJsonObject(i);
                                grades.setGrade(joGrade.getString("grade"));
                                grades.setScore(joGrade.getInt("score"));

                                date = new Date();
                                JsonObject joDate = joGrade.getJsonObject("date");
                                date.setYear(joDate.getInt("year"));
                                date.setMonth(joDate.getInt("month"));
                                date.setDay(joDate.getInt("day"));
                                grades.setDate(date);
                                listGrades.add(grades);
                            }
                        } else {
                            listRes = new ArrayList<>();
                        }

                        break;

                    case START_OBJECT:
                        if (keyName.equals("address")) {
                            address = new Address();
                            JsonObject joAdd = parser.getObject();
                            address.setBuilding(joAdd.getString("building"));
                            address.setStreet(joAdd.getString("street"));
                            address.setZipcode(joAdd.getString("zipcode"));

                            coord = new ArrayList<>();
                            JsonArray ja = joAdd.getJsonArray("coord");
                            for (int i = 0; i < ja.size(); i++) {
                                coord.add(ja.getJsonNumber(i).doubleValue());
                            }
                            address.setCoord(coord);
                        } else if ((keyName.equals("grades") || keyName.equals("score")) && res.getGrades() == null) {
                            grades = new Grades();
                        } else {
                            res = new Restaurant();
                        }
                        break;


                    case KEY_NAME:
                        keyName = parser.getString();
                        break;

                    case VALUE_STRING:
                        if (keyName.equals("restaurant_id")) {
                            res.setRestaurant_id(parser.getString());
                        } else if (keyName.equals("name")) {
                            res.setName(parser.getString());
                        } else if (keyName.equals("borough")) {
                            res.setBorough(parser.getString());
                        } else if (keyName.equals("cuisine")) {
                            res.setCuisine(parser.getString());
                        } else if (keyName.equals("grade")) {
                            grades.setGrade(parser.getString());
                        }

                        break;

                    case VALUE_NUMBER:
                        if (keyName.equals("score")) {
                            grades.setScore(parser.getInt());
                        } else if (keyName.equals("year")) {
                            date.setYear(parser.getInt());
                        } else if (keyName.equals("month")) {
                            date.setMonth(parser.getInt());
                        } else if (keyName.equals("day")) {
                            date.setDay(parser.getInt());
                        }

                        break;

                    case VALUE_TRUE:
                        res.setIs_closed(true);
                        break;
                    case VALUE_FALSE:
                        res.setIs_closed(false);
                        break;

                    case END_OBJECT:
                        res.setAddress(address);
                        res.setGrades(listGrades);


                        listRes.add(res);

                        break;
                    case END_ARRAY:
                        break;


                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return listRes;
    }


    public static List<Restaurant> find(String fileName) {
        List<Restaurant> listRes = null;
        Restaurant res = null;
        Address address = null;
        Grades grades = null;
        List<Grades> listGrades = null;
        List<Double> coord = null;
        Date date = null;
        String keyName = "";

        try (JsonParser parser = Json.createParser(new FileReader(fileName))) {
            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                switch (event) {
                    case START_ARRAY:
                        if (keyName.equals("grades")) {
                            listGrades = new ArrayList<>();
                            JsonArray ja = parser.getArray();
                            for (int i = 0; i < ja.size(); i++) {
                                grades = new Grades();
                                JsonObject joGrade = ja.getJsonObject(i);
                                grades.setGrade(joGrade.getString("grade"));
                                grades.setScore(joGrade.getInt("score"));

                                date = new Date();
                                JsonObject joDate = joGrade.getJsonObject("date");
                                date.setYear(joDate.getInt("year"));
                                date.setMonth(joDate.getInt("month"));
                                date.setDay(joDate.getInt("day"));
                                grades.setDate(date);
                                listGrades.add(grades);
                            }
                        } else {
                            listRes = new ArrayList<>();
                        }

                        break;

                    case START_OBJECT:
                        if (keyName.equals("address")) {
                            address = new Address();
                            JsonObject joAdd = parser.getObject();
                            address.setBuilding(joAdd.getString("building"));
                            address.setStreet(joAdd.getString("street"));
                            address.setZipcode(joAdd.getString("zipcode"));

                            coord = new ArrayList<>();
                            JsonArray ja = joAdd.getJsonArray("coord");
                            for (int i = 0; i < ja.size(); i++) {
                                coord.add(ja.getJsonNumber(i).doubleValue());
                            }
                            address.setCoord(coord);
                        } else if ((keyName.equals("grades") || keyName.equals("score")) && res.getGrades() == null) {
                            grades = new Grades();
                        } else {
                            res = new Restaurant();
                        }
                        break;


                    case KEY_NAME:
                        keyName = parser.getString();
                        break;

                    case VALUE_STRING:
                        if (keyName.equals("restaurant_id")) {
                            res.setRestaurant_id(parser.getString());
                        } else if (keyName.equals("name")) {
                            res.setName(parser.getString());
                        } else if (keyName.equals("borough")) {
                            res.setBorough(parser.getString());
                        } else if (keyName.equals("cuisine")) {
                            res.setCuisine(parser.getString());
                        } else if (keyName.equals("grade")) {
                            grades.setGrade(parser.getString());
                        }

                        break;

                    case VALUE_NUMBER:
                        if (keyName.equals("score")) {
                            grades.setScore(parser.getInt());
                        } else if (keyName.equals("year")) {
                            date.setYear(parser.getInt());
                        } else if (keyName.equals("month")) {
                            date.setMonth(parser.getInt());
                        } else if (keyName.equals("day")) {
                            date.setDay(parser.getInt());
                        }

                        break;

                    case VALUE_TRUE:
                        res.setIs_closed(true);
                        break;
                    case VALUE_FALSE:
                        res.setIs_closed(false);
                        break;

                    case END_OBJECT:
                        res.setAddress(address);
                        res.setGrades(listGrades);

                        if (res.getCuisine().equals("VN")) {
                            listRes.add(res);
                        }


                        break;
                    case END_ARRAY:
                        break;


                }
            }
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
        return listRes;
    }

    public static void toJsonFile(List<Restaurant> RestaurantS, String fileName) {
        try (JsonGenerator g = Json.createGenerator(new FileWriter(fileName))) {
            g.writeStartArray();
            for (Restaurant r : RestaurantS) {
                g.writeStartObject()
                        .write("restaurant_id", r.getRestaurant_id())
                        .write("is_closed", r.isIs_closed())
                        .write("name", r.getName())

                        .writeKey("address");


                Address add = r.getAddress();
                g.writeStartObject()
                        .write("building", add.getBuilding())
                        .write("street", add.getStreet())
                        .write("zipcode", add.getZipcode())
                        .writeKey("coord");

                        List<Double> coord = add.getCoord();
                            g.writeStartArray();
                            for (Double d : coord) {
                                g.write(d);

                            }
                            g.writeEnd();

                g.writeEnd();

                g.write("borough", r.getBorough());
                g.write("cuisine", r.getCuisine());




                List<Grades> grades = r.getGrades();
                g.writeKey("grades");
                    g.writeStartArray();
                    for (Grades gra : grades) {
                        g.writeStartObject()

                                .write("grade", gra.getGrade())
                                .write("score", gra.getScore());
                                Date date = gra.getDate();
                                g.writeKey("date");
                                g.writeStartObject()
                                        .write("year", date.getYear())
                                        .write("month", date.getMonth())
                                        .write("day", date.getDay());
                                g.writeEnd();
                        g.writeEnd();
                    }
                    g.writeEnd();

                g.writeEnd();

            }
            g.writeEnd();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }


    public static void main(String[] args) {
//        List<Restaurant> listRes = gg("FILE_JSON/data/res1.json");
//        System.out.println(listRes);


        List<Restaurant> listRes = readArr("FILE_JSON/data/RES_MANY_ARR.json");
        for (Restaurant r : listRes) {
            System.out.println(r);
        }
        List<Restaurant> aaa = find("FILE_JSON/data/RES_MANY_ARR.json");
        for (Restaurant r : aaa) {
            System.out.println(r);
        }


        toJsonFile(aaa, "FILE_JSON/data/VN.json");
    }
}
