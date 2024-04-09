package org.example.Stream;

import jakarta.json.*;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;
import org.example.entity.Address;
import org.example.entity.Person;
import org.example.entity.PhoneNumber;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JsonHandlerStream {

    // Xuat ra file json
    public static void toJsonFile(List<Person> persons, String fileName) {

        try (JsonGenerator gen = Json.createGenerator(new FileWriter(fileName))) {

            gen.writeStartArray();
            persons.forEach(p -> {
                Address add = p.getAddress();
                gen.writeStartObject()
                        .write("firstName", p.getFirstName())
                        .write("lastName", p.getLastName())
                        .write("age", p.getAge());

                if (add != null) {
                    gen.writeKey("address")
                            .writeStartObject()
                            .write("city", add.getCity())
                            .write("state", add.getState())
                            .write("postalCode", add.getPostalCode())
                            .writeEnd();
                }
                List<PhoneNumber> phones = p.getPhoneNumbers();
                if (phones != null) {
                    gen.writeKey("phoneNumbers")
                            .writeStartArray();
                    phones.forEach(item -> {
                        gen.writeStartObject()
                                .write("type", item.getType())
                                .write("number", item.getNumber())
                                .writeEnd();
                    });
                    gen.writeEnd();
                }

                gen.writeEnd();
            });

            gen.writeEnd();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    // Doc tu file json
    public static Person readFromFile(String fileName) {
        Person p = null;
        List<PhoneNumber> phones = null;
        Address add = null;
        String keyName = "";

        try (JsonParser parser = Json.createParser(new FileReader(fileName))) {

            while (parser.hasNext()) {
                Event event = parser.next();
                switch (event) {
                    case START_OBJECT:
                        if (keyName.equals("address")) {
                            add = new Address();
                            JsonObject joAdd = parser.getObject();
                            add.setStreetAddress(joAdd.getString("streetAddress"));
                            add.setCity(joAdd.getString("city"));
                            add.setState(joAdd.getString("state"));
                            add.setPostalCode(joAdd.getInt("postalCode"));
                        } else
                            p = new Person();
                        break;
                    case START_ARRAY:
                        phones = new ArrayList<>();
                        JsonArray ja = parser.getArray();
                        for (JsonValue jv : ja) {
                            JsonObject joPh = (JsonObject) jv;
                            phones.add(new PhoneNumber(
                                    joPh.getString("type"),
                                    joPh.getString("number")
                            ));
                        }
                        break;
                    case KEY_NAME:
                        keyName = parser.getString();
                        break;

                    case VALUE_STRING:
                        if (keyName.equals("firstName"))
                            p.setFirstName(parser.getString());
                         else if (keyName.equals("lastName"))
                            p.setLastName(parser.getString());
                        break;

                    case VALUE_NUMBER:
                        if ("age".equals(keyName))
                            p.setAge(parser.getInt());
                        else if ("postalCode".equals(keyName))
                            add.setPostalCode(parser.getInt());
                        break;

                    case END_OBJECT:
                        p.setAddress(add);
                        p.setPhoneNumbers(phones);
                        break;
                    case END_ARRAY:
                        break;

                    default:
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return p;
    }
    public static List<Person> read(String fileName) {
        Person p = null;
        List<PhoneNumber> phones = null;
        Address add = null;
        String keyName = "";
        List<Person> persons = null;

        try (JsonParser parser = Json.createParser(new FileReader(fileName))) {

            while (parser.hasNext()) {
                Event event = parser.next();
                switch (event) {
                    case START_OBJECT:
                        if (keyName.equals("address")) {
                            add = new Address();
                            JsonObject joAdd = parser.getObject();
                            add.setStreetAddress(joAdd.getString("streetAddress"));
                            add.setCity(joAdd.getString("city"));
                            add.setState(joAdd.getString("state"));
                            add.setPostalCode(joAdd.getInt("postalCode"));
                        } else
                            p = new Person();
                        break;
                    case START_ARRAY:
                        if(keyName.equals("phoneNumbers")){
                            phones = new ArrayList<>();
                            JsonArray ja = parser.getArray();
                            for (JsonValue jv : ja) {
                                JsonObject joPh = (JsonObject) jv;
                                phones.add(new PhoneNumber(
                                        joPh.getString("type"),
                                        joPh.getString("number")
                                ));
                            }
                        }else{
                            persons = new ArrayList<>();
                        }

                        break;
                    case KEY_NAME:
                        keyName = parser.getString();
                        break;

                    case VALUE_STRING:
                        if (keyName.equals("firstName"))
                            p.setFirstName(parser.getString());
                        else if (keyName.equals("lastName"))
                            p.setLastName(parser.getString());
                        break;

                    case VALUE_NUMBER:
                        if ("age".equals(keyName))
                            p.setAge(parser.getInt());
                        else if ("postalCode".equals(keyName))
                            add.setPostalCode(parser.getInt());
                        break;

                    case END_OBJECT:
                        p.setAddress(add);
                        p.setPhoneNumbers(phones);
                        persons.add(p);
                        break;
                    case END_ARRAY:
                        break;

                    default:
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return persons;
    }

}
