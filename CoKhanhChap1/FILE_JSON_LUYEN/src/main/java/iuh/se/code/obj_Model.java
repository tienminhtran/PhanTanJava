/*
 * @(#)obj_Model.java 1.0  26/2/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     26/2/2024
 *@Version:  1.0
 */


package iuh.se.code;

import iuh.se.entity.Address;
import iuh.se.entity.BenhNhan;
import iuh.se.entity.Date;
import iuh.se.entity.Tests;
import jakarta.json.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class obj_Model {

    public static List<BenhNhan> docBenhNhan(String fileName) {

        List<BenhNhan> list = new ArrayList<>();
        Address address = new Address();
        Date date = new Date();

        try (JsonReader reader = Json.createReader(new FileReader(fileName))) {
            JsonArray jsonArray = reader.readArray();
            for (JsonValue jsonValue : jsonArray) {
                JsonObject jsonObject = (JsonObject) jsonValue;


                BenhNhan benhNhan = new BenhNhan();
                benhNhan.set_id(jsonObject.getString("_id"));
                benhNhan.setFirst_Name(jsonObject.getString("first_name"));
                benhNhan.setLast_Name(jsonObject.getString("last_name"));
                benhNhan.setBlood_type(jsonObject.getString("blood_type"));
                benhNhan.setGender(jsonObject.getString("gender"));


                JsonObject jsonAddress = jsonObject.getJsonObject("address");
                address.setCity(jsonAddress.getString("city"));
                address.setDistrict(jsonAddress.getString("district"));
                address.setStreet(jsonAddress.getString("street"));
                address.setWard(jsonAddress.getString("ward"));
                benhNhan.setAddress(address);

                ArrayList<String> telephone = new ArrayList<>();
                JsonArray jsonArrayTelephone = jsonObject.getJsonArray("telephones");
                for (JsonValue jsonValue1 : jsonArrayTelephone) {
                    telephone.add(jsonValue1.toString());
                }
                benhNhan.setTelephone(telephone);

                ArrayList<Tests> listTests = new ArrayList<>();
                JsonArray jsonArrayTests = jsonObject.getJsonArray("tests");
                for (JsonValue jsonValue1 : jsonArrayTests) {
                    JsonObject jsonObjectTests = (JsonObject) jsonValue1;
                    Tests test = new Tests();
                    JsonObject jsonDate = jsonObjectTests.getJsonObject("date");
                    date.setDay(jsonDate.getInt("day"));
                    date.setMonth(jsonDate.getInt("month"));
                    date.setYear(jsonDate.getInt("year"));
                    test.setDate(date);
                    test.setResult(jsonObjectTests.getString("result"));
                    test.setTest_ID(jsonObjectTests.getInt("test_id"));
                    test.setTest_type(jsonObjectTests.getString("test_type"));
                    test.setCost(jsonObjectTests.getInt("cost"));
                    listTests.add(test);


                }
                benhNhan.setTests(listTests);
                benhNhan.setYear_of_birth(jsonObject.getInt("year_of_birth"));


                list.add(benhNhan);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public static void ghiBenhNhan(List<BenhNhan> benhNhanList, String fileName) {
        try (JsonWriter writer = Json.createWriter(new FileWriter(fileName))) {
            JsonObjectBuilder builder = Json.createObjectBuilder();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for (BenhNhan benhNhan : benhNhanList) {
                JsonObjectBuilder benhNhanObjBuilder = Json.createObjectBuilder();


                JsonArrayBuilder testsArrayBuilder = Json.createArrayBuilder();
                for (Tests tests : benhNhan.getTests()) {
                    JsonObjectBuilder testObjBuilder = Json.createObjectBuilder();

                    JsonObjectBuilder dateObjBuilder = builder
                            .add("day", tests.getDate().getDay())
                            .add("month", tests.getDate().getMonth())
                            .add("year", tests.getDate().getYear());
                    testObjBuilder.add("date", dateObjBuilder);

                    testObjBuilder.add("test_id", tests.getTest_ID());
                    testObjBuilder.add("test_type", tests.getTest_type());
                    testObjBuilder.add("result", tests.getResult());
                    testObjBuilder.add("cost", tests.getCost());

                    testsArrayBuilder.add(testObjBuilder);

                }

                JsonObjectBuilder addressObjBuilder = builder
                        .add("city", benhNhan.getAddress().getCity())
                        .add("district", benhNhan.getAddress().getDistrict())
                        .add("street", benhNhan.getAddress().getStreet())
                        .add("ward", benhNhan.getAddress().getWard());


                benhNhanObjBuilder.add("_id", benhNhan.get_id());
                benhNhanObjBuilder.add("first_name", benhNhan.getFirst_Name());
                benhNhanObjBuilder.add("last_name", benhNhan.getLast_Name());
                benhNhanObjBuilder.add("blood_type", benhNhan.getBlood_type());
                benhNhanObjBuilder.add("gender", benhNhan.getGender());


                JsonArrayBuilder telephoneArrayBuilder = Json.createArrayBuilder();
                for (String telephone : benhNhan.getTelephone()) {
                    telephoneArrayBuilder.add(telephone);
                }
                benhNhanObjBuilder.add("telephones", telephoneArrayBuilder);


                benhNhanObjBuilder.add("address", addressObjBuilder);
                benhNhanObjBuilder.add("tests", testsArrayBuilder);


                benhNhanObjBuilder.add("year_of_birth", benhNhan.getYear_of_birth());


                arrayBuilder.add(benhNhanObjBuilder);


            }
            writer.writeArray(arrayBuilder.build());


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        List<BenhNhan> list = docBenhNhan("FILE_JSON_LUYEN/data/benhNhan2.json");
        for (BenhNhan benhNhan : list) {
            System.out.println(benhNhan);
        }
//
//        ghiBenhNhan(list, "FILE_JSON_LUYEN/data/benhNhan2.json");
//        System.out.println("Ghi thanh cong");
    }
}
