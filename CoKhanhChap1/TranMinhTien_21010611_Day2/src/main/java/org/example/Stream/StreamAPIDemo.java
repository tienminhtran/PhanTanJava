package org.example.Stream;


import org.example.entity.Person;

import java.util.List;

public class StreamAPIDemo {
	
	public static void main(String[] args) {

		// đọc file dùng stream
//		Person p = JsonHandlerStream.readFromFile("TranMinhTien_21010611_Day2/data/person.json");
//		System.out.println(p);

		List<Person> persons = JsonHandlerStream.read("TranMinhTien_21010611_Day2/data/file.json");
		for(Person p: persons){
			System.out.println(p);
		}
	}
}
