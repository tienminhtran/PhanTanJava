package org.example;

import jakarta.json.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class JsonHandler {
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
}