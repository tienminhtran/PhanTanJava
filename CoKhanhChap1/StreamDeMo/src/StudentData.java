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
        students.add(new Student(1, "Khanh", "HCM", 20, "17A"));
        students.add(new Student(2, "Tien", "HCM", 20, "16B"));
        students.add(new Student(3, "Huy", "HCM", 20, "16B"));
        students.add(new Student(4, "Hieu", "HCM", 19, "16A"));
        students.add(new Student(5, "Hoa", "HCM", 20, "18D"));
        students.add(new Student(6, "Hanh", "HCM", 20, "18C"));
    }

    public static List<Student> getStudents() {
        return students;
    }
}
