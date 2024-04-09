/*
 * @(#)StudentDemo.java 1.0  31/12/2023
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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentDemo {
    public static void main(String[] args) {
        List<Student> Student = StudentData.getStudent();


        // CÁCH 1 DUNG STREAM
        System.out.println("\t \t DÙNG STREAM : ");
        List<Student> kq = Student.stream()
                .map(student -> {
                    student.setName(student.getName().toLowerCase());
                    return student;
                }).toList();
        kq.forEach(student -> System.out.println(student));

        System.out.println("\n\n\t \t DÙNG STREAM + FILTER: ");

        Student kq1 = Student.stream()
                .filter(student -> student.getName().startsWith("t"))
                .findAny()
                .orElse(null);
        System.out.println("Ket qua tim C1: " + kq1);


        Student kq2 = Student.stream()
                .filter(student -> {
                    if (student.getName().startsWith("t")) {
                        return true;
                    } else {
                        return false;
                    }

                })
                .findAny()
                .orElse(null);
        System.out.println("Ket qua tim C2: " + kq2);


        // DUNG LIST DE MO PHONG


        List<Student> kq3 = Student.stream()
                .filter(student -> student.getName().startsWith("t"))
                .collect(Collectors.toList());
        System.out.println("Ket qua tim C3: ");
        kq3.forEach(student -> System.out.println(student.toString()));

        //sort
        System.out.println("\t \t SORT + STREAM  tăng dân: ");

        List<Student> kq4 = Student.stream()
                .sorted(Comparator.comparing(student -> student.getName()))
                .collect(Collectors.toList());
        kq4.forEach(student -> System.out.println(student.toString()));

        // SORT GIAM
        System.out.println("\t \t SORT + STREAM  giảm dâ dùng reversed: ");

        List<Student> kq5 = Student.stream()
                .sorted(Comparator.comparing(student -> student.getName()))
                .toList().reversed();

        kq5.forEach(student -> System.out.println(student.toString()));

        // XOA


        // xóa vị tri1
        Student.removeIf(student -> Student.indexOf(student)==1);
        System.out.println(" SAU KHI XOA");
        System.out.println(Student.toString());

        // xoa find
        Student.removeIf(student -> student.getName().equals("tu"));
        System.out.println(" SAU KHI XOA");
        System.out.println(Student.toString());


        // THÊM
        System.out.println(" THÊM 1");
        Student.add(new Student(10, "Hoang", "TG", 23));
        Student.forEach(student -> System.out.println(student.toString()));

        System.out.println(" THÊM LIST");

        List<Student> st = new ArrayList<>();
        st.add(new Student(113, "Bao", "TG", 23));
        st.add(new Student(123, "Khanh", "TG", 12));
        st.add(new Student(3314, "Hoa", "TG", 24));
        st.add(new Student(122, "Bo", "TG", 13));
        System.out.println(st.toString());


        Student.addAll(st);



        // CÁCH 2 THÔNG THƯỜNG

        System.out.println("\t \t DÙNG THÔNG THƯỜNG : ");
        for (Student student : Student) {
            student.setName(student.getName().toUpperCase());
        }

        for (Student student : Student) {
            System.out.println(student);
        }


    }
}


