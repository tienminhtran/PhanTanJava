/*
 * @(#)stream_API.java 1.0  27/2/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     27/2/2024
 *@Version:  1.0
 */


package iuh.se.code;

import iuh.se.entity.Address;
import iuh.se.entity.BenhNhan;
import iuh.se.entity.Date;
import iuh.se.entity.Tests;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class stream_API {


    public static List<BenhNhan> docFile(String fileName) {
        List<BenhNhan> list = new ArrayList<>();
        Address address = new Address();
        List<String> telephones = new ArrayList<>();
        List<Tests> tests = new ArrayList<>();
        Tests test = new Tests();
        Date date = new Date();
        BenhNhan benhNhan = new BenhNhan();
        String keyName = "";
        try (JsonParser parser = Json.createParser(new FileReader(fileName))) {
            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                switch (event) {
                    case START_ARRAY:
                        if (keyName.equals("telephones")) {
                            telephones = new ArrayList<>();
                            JsonArray jsonArray = parser.getArray();
                            for (int i = 0; i < jsonArray.size(); i++) {
                                telephones.add(jsonArray.getString(i));
                            }
                            benhNhan.setTelephone(telephones);
                        } else if (keyName.equals("tests")) {
                            tests = new ArrayList<>();
                        } else if (keyName.equals("list")) {
                            list = new ArrayList<>();
                        }
                        break;
                    case START_OBJECT:
                        if (keyName.equals("address")) {
                            address = new Address();
                            JsonObject jsonObject = parser.getObject();
                            address.setCity(jsonObject.getString("city"));
                            address.setDistrict(jsonObject.getString("district"));
                            address.setStreet(jsonObject.getString("street"));
                            address.setWard(jsonObject.getString("ward"));

                        } else if (keyName.equals("date")) {
                            date = new Date();
                            JsonObject jsonObject = parser.getObject();
                            date.setDay(jsonObject.getInt("day"));
                            date.setMonth(jsonObject.getInt("month"));
                            date.setYear(jsonObject.getInt("year"));
                            test.setDate(date);


                        } else if ((keyName.equals("tests")  || keyName.equals("cost")) && benhNhan.getTests() == null) {
                            test = new Tests();
//                            tests.add(test);
                        } else {
                            benhNhan = new BenhNhan();
                        }
                        break;
                    case KEY_NAME:
                        keyName = parser.getString();
                        break;
                    case VALUE_STRING:
                        if (keyName.equals("_id")) {
                            benhNhan.set_id(parser.getString());
                        } else if (keyName.equals("first_name")) {
                            benhNhan.setFirst_Name(parser.getString());
                        } else if (keyName.equals("last_name")) {
                            benhNhan.setLast_Name(parser.getString());
                        } else if (keyName.equals("blood_type")) {
                            benhNhan.setBlood_type(parser.getString());
                        } else if (keyName.equals("gender")) {
                            benhNhan.setGender(parser.getString());
                        } else if (keyName.equals("result")) {
                            test.setResult(parser.getString());
                        } else if (keyName.equals("test_type")) {
                            test.setTest_type(parser.getString());
                        }
                        break;
                    case VALUE_NUMBER:
                        if (keyName.equals("cost")) {
                            test.setCost(parser.getInt());
                        } else if (keyName.equals("day")) {
                            date.setDay(parser.getInt());
                        } else if (keyName.equals("month")) {
                            date.setMonth(parser.getInt());
                        } else if (keyName.equals("year")) {
                            date.setYear(parser.getInt());
                        } else if (keyName.equals("year_of_birth")) {
                            benhNhan.setYear_of_birth(parser.getInt());
                        } else if (keyName.equals("test_id")) {
                            test.setTest_ID(parser.getInt());
                        }
                        break;
                    case END_ARRAY:
                        if(keyName.equals("cost")) {
                            benhNhan.setTests(tests);

                        }

                        break;
                    case END_OBJECT:
                        if(keyName.equals("ward")) {
                            benhNhan.setAddress(address);
                        } else if(keyName.equals("cost")|| benhNhan.getTests()==null) {
                            tests.add(test);
                        }
                        list.add(benhNhan);

                        break;


                }
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void ghi(List<BenhNhan> list, String fileName) {

        try (JsonGenerator gen = Json.createGenerator(new FileWriter(fileName))) {
            gen.writeStartArray();
            for (BenhNhan benhNhan : list) {
                gen.writeStartObject()
                        .write("_id", benhNhan.get_id())
                        .write("first_name", benhNhan.getFirst_Name())
                        .write("last_name", benhNhan.getLast_Name())
                        .write("blood_type", benhNhan.getBlood_type())
                        .write("gender", benhNhan.getGender())
                        .writeKey("address");

                gen.writeStartObject()
                        .write("city", benhNhan.getAddress().getCity())
                        .write("district", benhNhan.getAddress().getDistrict())
                        .write("street", benhNhan.getAddress().getStreet())
                        .write("ward", benhNhan.getAddress().getWard());
                gen.writeEnd();

                List<String> telephones = benhNhan.getTelephone();
                gen.writeKey("telephones");
                gen.writeStartArray();
                for (String telephone : telephones) {
                    gen.write(telephone);
                }
                gen.writeEnd();


                List<Tests> tests = benhNhan.getTests();

                gen.writeKey("tests");
                gen.writeStartArray();
                for (Tests test : tests) {
                    gen.writeStartObject();

                    Date date2 = test.getDate();
                    gen.writeKey("date");
                    gen.writeStartObject()
                            .write("day", date2.getDay())
                            .write("month", date2.getMonth())
                            .write("year", date2.getYear());


                    gen.writeEnd()

                            .write("test_id", test.getTest_ID())
                            .write("test_type", test.getTest_type())
                            .write("result", test.getResult())
                            .write("cost", test.getCost());
                    gen.writeEnd();
                }


                gen.writeEnd();


                gen.write("year_of_birth", benhNhan.getYear_of_birth());


                gen.writeEnd();
            }


            gen.writeEnd();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        List<BenhNhan> list = docFile("FILE_JSON_LUYEN/data/benhNhan.json");
        System.out.println(list);
//        ghi(list, "FILE_JSON_LUYEN/data/benhNhan3.json");


    }
}

