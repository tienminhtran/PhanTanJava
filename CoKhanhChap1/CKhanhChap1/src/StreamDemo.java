/*
 * @ (#) StreamDemo.java       26/12/2023
 *
 * Copyright (c) 2023 IUH. All rights reserved.
 */

import java.util.List;

/*
 * @description
 * @author: Khanh Nguyen
 * @date:   26/12/2023
 */
public class StreamDemo {

    public static void main(String[] args) {

        List<Student> students = StudentData.getStudents();
        List<Student> result = students
                .stream()
                .map(student -> {
                    student.setName(student.getName().toUpperCase());
                    return student;
                }).filter(student -> student.getName().startsWith("H"))
//                .findFirst()
//                .orElse(null);
                .toList();

        result.forEach(student -> System.out.println(student));

    }
}
