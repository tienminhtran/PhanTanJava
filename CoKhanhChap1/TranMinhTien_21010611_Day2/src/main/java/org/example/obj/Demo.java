/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     23/1/2024
 *@Version:  1.0
 */
package org.example.obj;


import org.example.entity.Address;
import org.example.entity.Person;
import org.example.entity.PhoneNumber;

import java.util.ArrayList;
import java.util.List;

public class Demo {

	public static void main(String[] args) {

		String json = "{\r\n"
				+ "    \"firstName\": \"John\",\r\n"
				+ "    \"lastName\": \"Smith\",\r\n"
				+ "    \"age\": 25,\r\n"
				+ "    \"address\": {\r\n"
				+ "        \"streetAddress\": \"21 2nd Street\",\r\n"
				+ "        \"city\": \"New York\",\r\n"
				+ "        \"state\": \"NY\",\r\n"
				+ "        \"postalCode\": 10021\r\n"
				+ "    },\r\n"
				+ "    \"phoneNumbers\": [\r\n"
				+ "        {\r\n"
				+ "            \"type\": \"home\",\r\n"
				+ "            \"number\": \"212 555-1234\"\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"type\": \"fax\",\r\n"
				+ "            \"number\": \"646 555-4567\" \r\n"
				+ "        }\r\n"
				+ "    ] \r\n"
				+ "}";

		// doc tu string -> java obj model
		System.out.println("doc tu string -> java obj model");
		Person p = JsonHandler.fromJsonObject(json);

		System.out.println(p);

		System.out.println("\n\ndoc tu file -> java obj model");
		// doc tu file -> java obj model
		Person p1 = JsonHandler.fromFile("TranMinhTien_21010611_Day2/data/person.json");
		System.out.println(p1);

		System.out.println("\n\ndoc tu doituong  tra string-> java obj model");

// Creating a Person object
		Person p2 = new Person("Tien", "Tran", 20);

		Address add = new Address("21 2nd Street", "New York", "NY", 10021);
		p2.setAddress(add);
		ArrayList<PhoneNumber> phones = new ArrayList<>();
		phones.add(new PhoneNumber("home", "212 555-1234"));
		phones.add(new PhoneNumber("fax", "646 555-4567"));
		p2.setPhoneNumbers(phones);
		String json2 = JsonHandler.toJson(p2);
		System.out.println(json2);


		// khoi tao chay voi mang json
		String filePath = "TranMinhTien_21010611_Day2/data/personArray.json";

		// Retrieve the list of persons from the JSON file
		List<Person> people = JsonHandler.fromJsonArray(filePath);
		int i = 0;
		for (Person person : people) {
			i++;
			System.out.println("Person ID: " + i);
			System.out.println("  First Name: " + person.getFirstName());
			System.out.println("  Last Name: " + person.getLastName());
			System.out.println("  Age: " + person.getAge());

			Address address = person.getAddress();
			System.out.println("  Address:");
			System.out.println("    Street Address: " + address.getStreetAddress());
			System.out.println("    City: " + address.getCity());
			System.out.println("    State: " + address.getState());
			System.out.println("    Postal Code: " + address.getPostalCode());

			List<PhoneNumber> phoneNumbers = person.getPhoneNumbers();
			System.out.println("  Phone Numbers:");
			for (PhoneNumber phoneNumber : phoneNumbers) {
				System.out.println("    Type: " + phoneNumber.getType());
				System.out.println("    Number: " + phoneNumber.getNumber());
			}

			System.out.println(); // Separate each person's information
		}


		System.out.println("\n\ndoc tu list -> string");
		List<Person> list = List.of(
				new Person("Ti", "Le", 20, new Address("12 nguyen Van Bao", "P4", "GV HCM", 10000),
						List.of(new PhoneNumber("home", "212 555-1234"), new PhoneNumber("fax", "646 555-4567"))),
				new Person("Le", "Le", 23, new Address("12455 nguyen Van Bao", "P4", "GV HCM", 10000))
		);

		String p3 = JsonHandler.toJson(list);
		System.out.println(p3);


		JsonHandler.toJsonFile(list, "TranMinhTien_21010611_Day2/data/persos_toJsonFile.json");

		System.out.println("JSON file created successfully.");

	}
}
