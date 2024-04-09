/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     23/1/2024
 *@Version:  1.0
 */
package org.example.obj;

import jakarta.json.*;
import org.example.entity.Address;
import org.example.entity.Person;
import org.example.entity.PhoneNumber;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonHandler {
    //file json, string (main) -> JsonRead ->
    // list, obj

    // ghi tu java obj -> string
    public static String toJson(Person p) {
        StringWriter st = null;
        try (JsonWriter writer = Json.createWriter(st = new StringWriter())) {
            JsonObjectBuilder oBuilder = Json.createObjectBuilder();


            Address add = p.getAddress();
            JsonObject joAdd = oBuilder
                    .add("streetAddress", add.getStreetAddress())
                    .add("city", add.getCity())
                    .add("state", add.getState())
                    .add("postalCode", add.getPostalCode())
                    .build();

            // còn list phoneNumbers
            p.setPhoneNumbers(new ArrayList<>());

            JsonArrayBuilder aBuilder = Json.createArrayBuilder();
            for (PhoneNumber ph : p.getPhoneNumbers()) {
                JsonObject joPh = oBuilder
                        .add("type", ph.getType())
                        .add("number", ph.getNumber())
                        .build();
                aBuilder.add(joPh);
            }


            JsonObject jo = oBuilder
                    .add("firstName", p.getFirstName())
                    .add("lastName", p.getLastName())
                    .add("age", p.getAge())
                    .add("Address", joAdd)
                    .add("phoneNumbers", aBuilder)
                    .build();
            writer.writeObject(jo);

        }
        return st.toString();
    }

    // XUAT FILE DUNG OBJ NHIEU DOI TUONG
    public static String toJson(List<Person> persons) {
        StringWriter st = new StringWriter();
        try (JsonWriter writer = Json.createWriter(st)) {
            JsonArrayBuilder aBuilder = Json.createArrayBuilder();

            for (Person p : persons) {
                Address add = p.getAddress();
                JsonObjectBuilder oBuilder = Json.createObjectBuilder();
                JsonObject joAdd = oBuilder
                        .add("streetAddress", add.getStreetAddress())
                        .add("city", add.getCity())
                        .add("state", add.getState())
                        .add("postalCode", add.getPostalCode())
                        .build();

                JsonArrayBuilder aBuilder1 = Json.createArrayBuilder();
                List<PhoneNumber> phoneNumbers = p.getPhoneNumbers();
                if (phoneNumbers != null) {
                    for (PhoneNumber ph : phoneNumbers) {
                        JsonObjectBuilder oBuilderPh = Json.createObjectBuilder();
                        JsonObject joPh = oBuilderPh
                                .add("type", ph.getType())
                                .add("number", ph.getNumber())
                                .build();
                        aBuilder1.add(joPh);
                    }
                }

                JsonObject jo = oBuilder
                        .add("firstName", p.getFirstName())
                        .add("lastName", p.getLastName())
                        .add("age", p.getAge())
                        .add("address", joAdd)
                        .add("phoneNumbers", aBuilder1)
                        .build();

                aBuilder.add(jo);
            }

            JsonArray ja = aBuilder.build();
            writer.writeArray(ja);
        }
        return st.toString();
    }



    // doc tu file -> list, DUNG ARRAY
    public static List<Person> fromJsonArray(String json) {
        List<Person> p = new ArrayList<>();

        try (JsonReader reader = Json.createReader(new FileReader(json))) {
            JsonArray jsonArray = reader.readArray();

            for (JsonValue jsonValue : jsonArray) {
                JsonObject jsonObject = (JsonObject) jsonValue;
                Person person = new Person();

                person.setFirstName(jsonObject.getString("firstName"));
                person.setLastName(jsonObject.getString("lastName"));
                person.setAge(jsonObject.getInt("age"));

                JsonObject addressJson = jsonObject.getJsonObject("address");
                Address address = new Address();
                address.setStreetAddress(addressJson.getString("streetAddress"));
                address.setCity(addressJson.getString("city"));
                address.setState(addressJson.getString("state"));
                address.setPostalCode(addressJson.getInt("postalCode"));
                person.setAddress(address);

                JsonArray phoneNumbersJson = jsonObject.getJsonArray("phoneNumbers");
                List<PhoneNumber> phoneNumbers = new ArrayList<>();

                for (JsonValue phoneNumberJson : phoneNumbersJson) {
                    JsonObject phoneNumberJsonObject = (JsonObject) phoneNumberJson;
                    PhoneNumber phoneNumber = new PhoneNumber();
                    phoneNumber.setType(phoneNumberJsonObject.getString("type"));
                    phoneNumber.setNumber(phoneNumberJsonObject.getString("number"));
                    phoneNumbers.add(phoneNumber);
                }

                person.setPhoneNumbers(phoneNumbers);
                p.add(person);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return p;
    }


    // DOC FILE DÙNG OBJECT TREN 1 DOI TUONG

    public static Person fromJsonObject(String json) {

        Person p = null;

        Address add = null;

        PhoneNumber ph = null;
        try (JsonReader reader = Json.createReader(new StringReader(json))) {

            JsonObject jo = reader.readObject();
            if (jo != null) {
                p = new Person();
                p.setFirstName(jo.getString("firstName"));
                p.setLastName(jo.getString("lastName"));
                p.setAge(jo.getInt("age"));

                JsonObject joAdd = jo.getJsonObject("address");
                add = new Address();
                add.setStreetAddress(joAdd.getString("streetAddress"));
                add.setCity(joAdd.getString("city"));
                add.setState(joAdd.getString("state"));
                add.setPostalCode(joAdd.getInt("postalCode"));
                p.setAddress(add);

                JsonArray ja = jo.getJsonArray("phoneNumbers");
                ArrayList<PhoneNumber> phones = new ArrayList<>();
                for (JsonValue jv : ja) {
                    JsonObject joPh = (JsonObject) jv;
                    ph = new PhoneNumber();
                    ph.setType(joPh.getString("type"));
                    ph.setNumber(joPh.getString("number"));
                    phones.add(ph);
                }
                p.setPhoneNumbers(phones);
            }
        }
        return p;
    }


    //--------------------------------------------------------------------------------------
    // doc len tu file json
    // doc tu file -> java obj model
    public static Person fromFile(String fileName) {
        Person p = null;
        Address add = null;
        try (JsonReader reader = Json.createReader(new FileReader(fileName));) {

            JsonObject jo = reader.readObject();
            if (jo != null) {
                p = new Person();
                p.setFirstName(jo.getString("firstName"));
                p.setLastName(jo.getString("lastName"));
                p.setAge(jo.getInt("age"));

                JsonObject joAdd = jo.getJsonObject("address");
                add = new Address();
                add.setStreetAddress(joAdd.getString("streetAddress"));
                add.setCity(joAdd.getString("city"));
                add.setState(joAdd.getString("state"));
                add.setPostalCode(joAdd.getInt("postalCode"));
                p.setAddress(add);

                JsonArray ja = jo.getJsonArray("phoneNumbers");
                ArrayList<PhoneNumber> phones = new ArrayList<>();
                PhoneNumber ph = null;
                ph = new PhoneNumber();
                for (JsonValue jv : ja) {
                    JsonObject joPh = (JsonObject) jv;
                    ph.setType(joPh.getString("type"));
                    ph.setNumber(joPh.getString("number"));
//					PhoneNumber ph = new PhoneNumber(joPh.getString("type"),joPh.getString("number"));
                    phones.add(ph);
                }
                p.setPhoneNumbers(phones);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;

    }

    // ghi tu java obj -> file
    public static void toJsonFile(List<Person> persons, String fileName) {
        try (
                JsonWriter writer = Json.createWriter(new FileWriter(fileName)
                );
        ) {

            JsonArrayBuilder aBuilder = Json.createArrayBuilder();
            JsonObjectBuilder oBuilder = Json.createObjectBuilder();

            for (Person p : persons) {

                Address add = p.getAddress();
                JsonObject joAdd = oBuilder.add("name", add.getStreetAddress())
                        .add("city", add.getCity())
                        .build();


                JsonObject jo = oBuilder
                        .add("firstName", p.getFirstName())
                        .add("lastName", p.getLastName())
                        .add("age", p.getAge())
                        .add("address", joAdd)
                        .build();

                aBuilder
                        .add(jo);
            }

            JsonArray ja = aBuilder.build();
            writer.write(ja);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//autoboxing 
//unboxing

//upcasting
//downcasting
