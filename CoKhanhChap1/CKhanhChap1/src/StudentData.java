/*
 * @ (#) StudentData.java       26/12/2023
 *
 * Copyright (c) 2023 IUH. All rights reserved.
 */

import java.util.ArrayList;
import java.util.List;

/*
 * @description
 * @author: Khanh Nguyen
 * @date:   26/12/2023
 */
public class StudentData {
    private static List<Student> students = new ArrayList<>();

    static {
        students.add(new Student(1, "Khanh", "HCM", 20));
        students.add(new Student(2, "Hoa", "HCM", 20));
        students.add(new Student(3, "huy", "HCM", 20));
        students.add(new Student(4, "Hieu", "HCM", 20));
        students.add(new Student(5, "hien", "HCM", 20));
        students.add(new Student(6, "Hung", "HCM", 20));
        students.add(new Student(7, "Hai", "HCM", 20));
    }

    public static List<Student> getStudents() {
        return students;
    }
}
