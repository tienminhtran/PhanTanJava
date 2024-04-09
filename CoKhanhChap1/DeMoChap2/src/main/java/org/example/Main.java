/*
 * @(#)Main.java 1.0  22/1/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     22/1/2024
 *@Version:  1.0
 */
package org.example;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        // khoi tao chay voi doi tuong json
        Person p = JsonHandler.fromFile("D:\\BAI_TAP_LTPT_DHKTPM17A\\CoKhanhChap1\\Chap\\src\\main\\data\\person.json");
        System.out.println(p);

        // khoi tao chay voi mang json
        String filePath = "D:\\BAI_TAP_LTPT_DHKTPM17A\\CoKhanhChap1\\Chap\\src\\main\\data\\personArray.json";

        // Retrieve the list of persons from the JSON file
        List<Person> people = JsonHandlerArray.fromFile(filePath);

        // Display information for each person
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
    }
}