package demo.js;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import demo.entity.Address;
import demo.entity.Person;
import demo.entity.PhoneNumber;
import jakarta.json.*;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;

public class JsonHandlerStream {

    public static void toJsonFile(List<Person> persons, String fileName) {

        try (JsonGenerator gen = Json.createGenerator(new FileWriter(fileName))) {

            gen.writeStartArray();

            for (Person p : persons) {



                gen.writeStartObject()
                        .write("lastName", p.getLastName())
                        .write("firstName", p.getFirstName())
                        .write("age", p.getAge());
                Address add = p.getAddress();
                if (add != null) {
                    gen.writeKey("address")
                            .writeStartObject()
                            .write("streetAddress", add.getStreetAddress())
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

            }

            gen.writeEnd();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static Person readFromFile(String fileName) {

        Person p = null;
        List<PhoneNumber> phones = null;
        String keyName = "";
        Address add = null;

        try (JsonParser parser = Json.createParser(new FileReader(fileName));) {

            while (parser.hasNext()) {
                Event event = parser.next();
                switch (event) {
                    case START_OBJECT:
                        if (keyName.equals("address")) {
                            add = new Address();
                            JsonObject jsonObject = parser.getObject();
                            add.setStreetAddress(jsonObject.getString("streetAddress"));
                            add.setCity(jsonObject.getString("city"));
                            add.setState(jsonObject.getString("state"));
                            add.setPostalCode(jsonObject.getInt("postalCode"));
                        } else
                            p = new Person();
                        break;
                    case START_ARRAY:
                        phones = new ArrayList<>();
                        JsonArray ja = parser.getArray();
                        for (JsonValue jv : ja) {
                            JsonObject joPh = (JsonObject) jv;
                            phones.add(new PhoneNumber(joPh.getString("type"), joPh.getString("number")));
                        }
                        break;
                    case KEY_NAME:
                        keyName = parser.getString();
                        break;

                    case VALUE_STRING:
                        String stringValue = parser.getString();

                        switch (keyName) {
                            case "firstName":
                                p.setFirstName(stringValue);
                                break;
                            case "lastName":
                                p.setLastName(stringValue);
                                break;
                        }

                        break;

                    case VALUE_NUMBER:
                        JsonValue jsonValue = parser.getValue();
                        if (jsonValue instanceof JsonNumber) {
                            JsonNumber jsonNumber = (JsonNumber) jsonValue;

                            if (keyName.equals("age")) {
                                p.setAge(jsonNumber.intValue());
                            }

                        }
                        break;

//				case VALUE_FALSE:
//					break;
//					
//				case VALUE_NULL:
//					break;
//					
//				case VALUE_TRUE:
//					break;
//					
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

        } catch (
                IOException e) {
            e.printStackTrace();
        }

        return p;
    }
}
