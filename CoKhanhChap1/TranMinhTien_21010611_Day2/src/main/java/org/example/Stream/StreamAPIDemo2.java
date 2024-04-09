package org.example.Stream;

import org.example.entity.Address;
import org.example.entity.Person;

import java.util.List;



public class StreamAPIDemo2 {
	
	public static void main(String[] args) {

		// ghi xuong file dung stream
		List<Person> list = List.of(
				new Person("Ti", "Le", 20),
				new Person("Le", "Le", 23, new Address("12455 nguyen Van Bao", "P4", "GV HCM", 10000)),
				new Person("Tien", "Tran", 20, new Address("12455 nguyen Van Bao", "P4", "GV HCM", 10000))
		);


		JsonHandlerStream.toJsonFile(list, "TranMinhTien_21010611_Day2/data/toJsonFile_STREAM.json");
		System.out.println("Done!");
	}
}
