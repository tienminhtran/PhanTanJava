package demo.jom;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import demo.entity.Address;
import demo.entity.Person;
import demo.entity.PhoneNumber;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import jakarta.json.JsonWriter;

public class JsonHandler {

    public static String toJson(Person p) {
        StringWriter st = null;

        try (JsonWriter writer = Json.createWriter(st = new StringWriter())) {

            JsonObjectBuilder oBuilder = Json.createObjectBuilder();

            Address address = p.getAddress();
            JsonObject jsonObjAddress = oBuilder
                    .add("streetAddress", address.getStreetAddress())
                    .add("city", address.getCity())
                    .add("state", address.getState())
                    .add("postalCode", address.getPostalCode())
                    .build();

            List<PhoneNumber> phones = p.getPhoneNumbers();
            JsonArrayBuilder aBuilder = Json.createArrayBuilder();
            phones.forEach(phoneNumber -> {
                JsonObject jsonObjPhone = oBuilder
                        .add("type", phoneNumber.getType())
                        .add("number", phoneNumber.getNumber())
                        .build();
                aBuilder.add(jsonObjPhone);
            });


            JsonObject jo = oBuilder
                    .add("firstName", p.getFirstName())
                    .add("lastName", p.getLastName())
                    .add("age", p.getAge())
                    .add("address", jsonObjAddress)
                    .add("phoneNumbers", aBuilder)
                    .build();

            writer.writeObject(jo);
        }

        return st.toString();
    }

    public static String toJson(List<Person> persons) {
        StringWriter st = null;


        try (JsonWriter writer = Json.createWriter(st = new StringWriter())) {
            JsonArrayBuilder aBuilder = Json.createArrayBuilder();
            JsonObjectBuilder oBuilder = Json.createObjectBuilder();

            persons.forEach(p -> {
                //Data address
                Address address = p.getAddress();
                JsonObject jsonAddress = null;
                if (address != null) {
                    jsonAddress = oBuilder
                            .add("streetAddress", address.getStreetAddress())
                            .add("city", address.getCity())
                            .add("state", address.getState())
                            .add("postalCode", address.getPostalCode())
                            .build();
                }

                //Data Phonenumber
                JsonArrayBuilder aBuilder_phone = Json.createArrayBuilder();
                List<PhoneNumber> phones = p.getPhoneNumbers();
                if (phones != null) {
                    phones.forEach(item -> {
                        JsonObject jsonPhone = oBuilder
                                .add("type", item.getType())
                                .add("number", item.getNumber())
                                .build();
                        aBuilder_phone.add(jsonPhone);
                    });
                }

                JsonObject jsonObject = oBuilder
                        .add("firstName", p.getFirstName())
                        .add("lastName", p.getLastName())
                        .add("age", p.getAge())
                        .add("address", jsonAddress)
                        .add("phoneNumbers", aBuilder_phone)
                        .build();

                    aBuilder.add(jsonObject);
            });
            JsonArray jsonArray = aBuilder.build();
            writer.writeArray(jsonArray);
        }
        return st.toString();
    }

    public static List<Person> fromJsonArray(String json) {
        Person p = null;
        List<Person> list = new ArrayList<>();
        try (JsonReader reader = Json.createReader(new StringReader(json))) {
            JsonObject jsonObject = reader.readObject();
            if (jsonObject != null) {
                p = new Person();
                p.setFirstName(jsonObject.getString("firstName"));
                p.setLastName(jsonObject.getString("lastName"));
                p.setAge(jsonObject.getInt("age"));

                Address address = new Address();
                JsonObject jsonObjAddress = jsonObject.getJsonObject("address");
                address.setStreetAddress(jsonObjAddress.getString("streetAddress"));
                address.setCity(jsonObjAddress.getString("city"));
                address.setState(jsonObjAddress.getString("state"));
                address.setPostalCode(jsonObjAddress.getInt("postalCode"));

                p.setAddress(address);

                List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
                JsonArray jsonArPhone = jsonObject.getJsonArray("phoneNumbers");
                jsonArPhone.forEach(item -> {
                    JsonObject jsonObjPhone = (JsonObject) item;
                    PhoneNumber phoneNumber = new PhoneNumber();
                    phoneNumber.setType(jsonObjPhone.getString("type"));
                    phoneNumber.setNumber(jsonObjPhone.getString("number"));

                    phoneNumbers.add(phoneNumber);
                });
                p.setPhoneNumbers(phoneNumbers);
                list.add(p);
            }
        }


        return list;
    }

    public static Person fromJsonObject(String json) {

        Person p = null;

        try (JsonReader reader = Json.createReader(new StringReader(json))) {

            JsonObject jo = reader.readObject();
            if (jo != null) {
                p = new Person();
                p.setFirstName(jo.getString("firstName"));
                p.setAge(jo.getInt("age"));

            }

        }

        return p;
    }


    public static Person fromFile(String fileName) {

        Person p = null;

        Address add = null;

        try (
                JsonReader reader = Json.createReader(
                        new FileReader(fileName));
        ) {

            JsonObject jo = reader.readObject();
            if (jo != null) {
                p = new Person();
                p.setFirstName(jo.getString("firstName"));
                p.setLastName(jo.getString("lastName"));
                p.setAge(jo.getInt("age"));

                JsonObject joAdd = jo.getJsonObject("address");

                add = new Address();
                add.setStreetAddress(joAdd.getString("streetAddress"));

                p.setAddress(add);

                JsonArray ja = jo.getJsonArray("phoneNumbers");
                List<PhoneNumber> phones = new ArrayList<>();

                for (JsonValue jv : ja) {
                    JsonObject joPh = (JsonObject) jv;
                    PhoneNumber ph = new PhoneNumber(joPh.getString("type"),
                            joPh.getString("number"));
                    phones.add(ph);
                }

                p.setPhoneNumbers(phones);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return p;

    }

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
