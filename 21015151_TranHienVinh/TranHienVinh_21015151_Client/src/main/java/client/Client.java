/*
 * @ {#} Client.java   1.0     02/04/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package client;

import entity.Course;
import entity.Department;
import entity.OnlineCourse;
import entity.Student;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   02/04/2024
 * @version:    1.0
 */
public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("Vinh", 5151);
        Scanner sc=new Scanner(System.in);
//        Gửi yêu cầu tới server
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//        Nhận kết quả từ server
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
        ) {
            while (true){
                System.out.println("Menu");
                System.out.println("1. Search course by title");
                System.out.println("2. Add new online course");
                System.out.println("3. Get all courses");
                System.out.println("4. Find course by id");
                System.out.println("5. Update online course");
                System.out.println("6. Delete course by id");
                System.out.println("7. Exists course by id");
                System.out.println("8. Find course with max credits");
                System.out.println("9. Find all students");
                System.out.println("10. Find by enrollment date in year");
                System.out.println("11. Find students enrolled in course");
                System.out.println("12. Find by enrollment date");
                System.out.println("13. Find department by id");
                System.out.println("14. Add department");
                System.out.println("15. Find department with budget greater than threshold");
                System.out.println("0. Exit");
                System.out.printf("Choose: ");
                int choice = sc.nextInt();
                dos.writeInt(choice);
                switch (choice){
                    case 0:
                        System.exit(0);
                        break;
                    case 1: // Find course by title
                        sc.nextLine();
                        System.out.printf("Enter course title: ");
                        String title = sc.nextLine();
                        dos.writeUTF(title);
                        dos.flush();

                        List<Course> courses = (List<Course>) ois.readObject();
                        for (Course course: courses){
                            System.out.println(course);
                        }
                        break;
                    case 2: // Add new online course
                        sc.nextLine();
                        System.out.println("Add new online course");
                        System.out.printf("Enter name course: ");
                        String name = sc.nextLine();
                        System.out.printf("Enter credits: ");
                        int credits = Integer.parseInt(sc.nextLine());
                        System.out.printf("Enter url: ");
                        String url = sc.nextLine();
                        System.out.printf("Enter departmentId: ");
                        int departmentId = Integer.parseInt(sc.nextLine());
                        dos.writeUTF(name);
                        dos.writeInt(credits);
                        dos.writeUTF(url);
                        dos.writeInt(departmentId);
                        dos.flush();

                        boolean result = ois.readBoolean();
                        if (result){
                            System.out.println("Add course successfully");
                        }else {
                            System.out.println("Add course failed");
                        }
                        break;
                    case 3: //get all courses
                        List<Course> courseList = (List<Course>) ois.readObject();
                        for (Course course: courseList){
                            System.out.println(course);
                        }
                        break;
                    case 4:
                        sc.nextLine();
                        System.out.printf("Enter course id: ");
                        int courseId = Integer.parseInt(sc.nextLine());
                        dos.writeInt(courseId);
                        dos.flush();

                        Course course1 = (Course) ois.readObject();
                        System.out.println(course1);
                        break;
                    case 5: //Update online course
                        sc.nextLine();
                        System.out.printf("Enter course id: ");
                        int id1 = Integer.parseInt(sc.nextLine());
                        System.out.printf("Enter name course: ");
                        String title1 = sc.nextLine();
                        System.out.printf("Enter credits: ");
                        int credits1 = Integer.parseInt(sc.nextLine());
                        System.out.printf("Enter url: ");
                        String url1 = sc.nextLine();
                        System.out.printf("Enter departmentId: ");
                        int departmentId1 = Integer.parseInt(sc.nextLine());
                        dos.writeInt(id1);
                        dos.writeUTF(title1);
                        dos.writeInt(credits1);
                        dos.writeUTF(url1);
                        dos.writeInt(departmentId1);
                        dos.flush();

                        boolean result1 = ois.readBoolean();
                        if (result1){
                            System.out.println("Update course successfully");
                        }else {
                            System.out.println("Update course failed");
                        }
                        break;
                    case 6: //delete course
                        sc.nextLine();
                        System.out.printf("Enter course id: ");
                        int id2 = Integer.parseInt(sc.nextLine());
                        dos.writeInt(id2);
                        dos.flush();

                        boolean result2 = ois.readBoolean();
                        if (result2){
                            System.out.println("Delete course successfully");
                        }else {
                            System.out.println("Delete course failed");
                        }
                        break;
                    case 7: //exists course by id
                        sc.nextLine();
                        System.out.printf("Enter course id: ");
                        int courseID = Integer.parseInt(sc.nextLine());
                        dos.writeInt(courseID);
                        dos.flush();
                        boolean exists = ois.readBoolean();
                        if (exists){
                            System.out.println("Course exists");
                        }else {
                            System.out.println("Course not exists");
                        }
                        break;
                    case 8: //find course with max credits
                        List<Course> courseList1 = (List<Course>) ois.readObject();
                        for (Course course: courseList1){
                            System.out.println(course);
                        }
                        break;
                    case 9://find all students
                        List<Student> studentList = (List<Student>) ois.readObject();
                        for (Student student: studentList){
                            System.out.println(student);
                        }
                        break;
                    case 10://find by enrollment date in year
                        sc.nextLine();
                        System.out.printf("Enter year: ");
                        int year = Integer.parseInt(sc.nextLine());
                        dos.writeInt(year);
                        dos.flush();
                        List<Student> studentList1 = (List<Student>) ois.readObject();
                        for (Student student: studentList1){
                            System.out.println(student);
                        }
                        break;
                    case 11: //find students enrolled in course
                        sc.nextLine();
                        System.out.printf("Enter course title: ");
                        String ti = sc.nextLine();
                        dos.writeUTF(ti);
                        dos.flush();
                        List<Student> studentList2 = (List<Student>) ois.readObject();
                        for (Student student: studentList2){
                            System.out.println(student);
                        }
                        break;
                    case 12://find by enrollment date
                        sc.nextLine();
                        System.out.printf("Enter year: ");
                        int year1 = Integer.parseInt(sc.nextLine());
                        System.out.printf("Enter month: ");
                        int month = Integer.parseInt(sc.nextLine());
                        System.out.printf("Enter day: ");
                        int day = Integer.parseInt(sc.nextLine());
                        dos.writeInt(year1);
                        dos.writeInt(month);
                        dos.writeInt(day);
                        dos.flush();
                        List<Student> studentList3 = (List<Student>) ois.readObject();
                        for (Student student: studentList3){
                            System.out.println(student);
                        }
                        break;
                    case 13: //find department by id
                        sc.nextLine();
                        System.out.printf("Enter department id: ");
                        int departID = Integer.parseInt(sc.nextLine());
                        dos.writeInt(departID);
                        Department department = (Department) ois.readObject();
                        System.out.println(department);
                        break;
                    case 14://add department
                        sc.nextLine();
                        System.out.println("Add new department");
                        System.out.printf("Enter name department: ");
                        String name1 = sc.nextLine();
                        System.out.printf("Enter budget: ");
                        double budget = Double.parseDouble(sc.nextLine());
                        System.out.printf("Enter year: ");
                        int year2 = Integer.parseInt(sc.nextLine());
                        System.out.printf("Enter month: ");
                        int month2 = Integer.parseInt(sc.nextLine());
                        System.out.printf("Enter day: ");
                        int day2 = Integer.parseInt(sc.nextLine());
                        System.out.printf("Enter administator: ");
                        int admin = Integer.parseInt(sc.nextLine());
                        dos.writeUTF(name1);
                        dos.writeDouble(budget);
                        dos.writeInt(year2);
                        dos.writeInt(month2);
                        dos.writeInt(day2);
                        dos.writeInt(admin);
                        dos.flush();
                        boolean result4 = ois.readBoolean();
                        if (result4){
                            System.out.println("Add department successfully");
                        }else {
                            System.out.println("Add department failed");
                        }
                        break;
                    case 15: //find department with budget greater than threshold
                        sc.nextLine();
                        System.out.printf("Enter budget: ");
                        double threshold = Double.parseDouble(sc.nextLine());
                        dos.writeDouble(threshold);
                        dos.flush();
                        List<Department> departmentList = (List<Department>) ois.readObject();
                        for (Department department1: departmentList){
                            System.out.println(department1);
                        }
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
