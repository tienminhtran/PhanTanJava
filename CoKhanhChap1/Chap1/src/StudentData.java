/*
 * @(#)StudentData.java 1.0  31/12/2023
 * Copy right (c) 2023 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     31/12/2023
 *@Version:  1.0
 */


import java.util.ArrayList;
import java.util.List;

public class StudentData {
    public static List<Student> Student = new ArrayList<>();

    public static List<Student> getStudent() {
        return Student;
    }

//    static {
//        Student.add(new Student(1,"Tien","HCM",20));
//        Student.add(new Student(2,"A","HCM",20));
//        Student.add(new Student(3,"B","HCM",20));
//        Student.add(new Student(4,"C","HCM",20));
//    }


    static {
        Student s1 = new Student(1, "Tien", "HCM", 20);
        Student s2 = new Student(2, "Hoa", "HCM", 20);
        Student s3 = new Student(3, "An", "HCM", 20);
        Student s4 = new Student(33, "Tu", "HCM", 20);

        Student.add(s1);
        Student.add(s2);
        Student.add(s3);
        Student.add(s4);
    }


}
