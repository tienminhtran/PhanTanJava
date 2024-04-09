package demo.jom;

import java.util.ArrayList;
import java.util.List;

import demo.entity.Address;
import demo.entity.Person;
import demo.entity.PhoneNumber;

public class ObjectModelAPIDemo {
	public static void main(String[] args) {

		List<PhoneNumber> pnList = new ArrayList<PhoneNumber>();
		pnList.add(new PhoneNumber("home", "123456789"));

		List<Person> list = List.of(
				new Person("Ti", "Le", 20,new Address("12455 nguyen Van Bao", "P4", "GV HCM", 10000), pnList),
				new Person("Le", "Le", 23, new Address("12455 nguyen Van Bao", "P4", "GV HCM", 10000), pnList)
		);
//
//		JsonHandler.toJsonFile(list, "data/people.json");

		//Chuyen doi tu doituong sang object
//		Person p = new Person("Ti", "Le", 20, new Address("12 nguyen Van Bao", "P4", "GV HCM", 10000), pnList);
//		System.out.println(JsonHandler.toJson(p));

		//Chuyen doi tu list doi tuong sang objcect
//		System.out.println(JsonHandler.toJson(list));
		
		//Doc tu doi tuong chuyen sang List doi tuong
		String json = "{\n" +
				"    \"firstName\": \"John\",\n" +
				"    \"lastName\": \"Smith\",\n" +
				"    \"age\": 25,\n" +
				"    \"address\": {\n" +
				"        \"streetAddress\": \"21 2nd Street\",\n" +
				"        \"city\": \"New York\",\n" +
				"        \"state\": \"NY\",\n" +
				"        \"postalCode\": 10021\n" +
				"    },\n" +
				"    \"phoneNumbers\": [\n" +
				"        {\n" +
				"            \"type\": \"home\",\n" +
				"            \"number\": \"212 555-1234\"\n" +
				"        },\n" +
				"        {\n" +
				"            \"type\": \"fax\",\n" +
				"            \"number\": \"646 555-4567\" \n" +
				"        }\n" +
				"    ] \n" +
				"}";
		JsonHandler.fromJsonArray(json).forEach(System.out::println);

		//Doc tu file json add vao doituong
//		Person p = JsonHandler.fromFile("data/person.json");
//		System.out.println(p);
		
	
//		try(
//				JsonReader reader = Json.createReader(
//						new FileReader("data/person.json"));
//				){
			
//			reader.readObject()
//			.entrySet()
//			.forEach(entry -> {
//				System.out.println("Key: " + entry.getKey() + ", value: " + entry.getValue());
//			});
			
//		}catch (IOException e) {
//			e.printStackTrace();
//		}
		
	}
}

//Checked Exception
//Unchecked Exception --> RuntimeException
