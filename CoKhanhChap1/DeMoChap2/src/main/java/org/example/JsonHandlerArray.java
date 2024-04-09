/*
 * @(#)JsonHandlerArray.java 1.0  22/1/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     22/1/2024
 *@Version:  1.0
 */


package org.example;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class JsonHandlerArray {
    public static List<Person> fromFile(String fileName) {
        List<Person> persons = new ArrayList<>();

        try (JsonReader reader = Json.createReader(new FileReader(fileName))) {
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
                persons.add(person);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return persons;
    }
}
