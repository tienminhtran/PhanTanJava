// DÙNG STREAM ĐỂ ĐỌC FILE JSON

package org.example;

public class StreamAPIDemo {
	
	public static void main(String[] args) {
		
		Person p = JsonHandlerStream.readFromFile("data/person.json");
		
		System.out.println(p);
		
	}
	
}
