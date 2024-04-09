package demo.js;

import demo.entity.Person;

public class StreamAPIDemo {
	
	public static void main(String[] args) {
		
		Person p = JsonHandlerStream.readFromFile("JSON_S/data/person.json");
		
		System.out.println(p);
		
	}
	
}
