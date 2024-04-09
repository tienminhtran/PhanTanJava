package demo.jom;

import demo.entity.Person;

public class Demo1 {
	public static void main(String[] args) {
		
		Person p = new Person("John", "Smith", 25);
		String json = JsonHandler.toJson(p);
		System.out.println(json);
		
	}
}
