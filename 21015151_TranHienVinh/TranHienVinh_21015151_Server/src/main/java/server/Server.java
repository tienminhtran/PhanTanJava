/*
 * @ {#} Server.java   1.0     02/04/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package server;

import dao.CourseDAO;
import dao.DepartmentDAO;
import dao.StudentDAO;
import dao.impl.CourseImpl;
import dao.impl.DepartmentImpl;
import dao.impl.StudentImpl;
import entity.Course;
import entity.Department;
import entity.OnlineCourse;
import entity.Student;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Handler;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   02/04/2024
 * @version:    1.0
 */
public class Server {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(5151)) {
            System.out.println("Ready...");
            while (true){
                Socket socket = serverSocket.accept();
                Server server = new Server();
                System.out.println("Connected to client: "+socket.getInetAddress().getHostName());
                new Thread(server.new Handler(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public class Handler implements Runnable{
        private Socket socket;
        private CourseDAO courseDAO;
        private DepartmentDAO departmentDAO;
        private StudentDAO studentDAO;
        public Handler(Socket socket){
            this.socket = socket;
            courseDAO = new CourseImpl();
            departmentDAO = new DepartmentImpl();
            studentDAO = new StudentImpl();
        }
        @Override
        public void run() {
            try{
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                while (true){
                    int choice = dis.readInt();
                    switch (choice){
                        case 1: // Find course by title
                            String title = dis.readUTF();
                            System.out.println("Title: "+title);
                            List<Course> courses = courseDAO.findCourseByTitle(title);
                            oos.writeObject(courses);
                            oos.flush();
                            break;
                        case 2: //add course
                            String name = dis.readUTF();
                            int credits = dis.readInt();
                            String url = dis.readUTF();
                            int departmentId = dis.readInt();
                            Department department = departmentDAO.findById(departmentId);
                            System.out.println("Department: "+department);
                            if(department==null){
                                oos.writeBoolean(false);
                                oos.flush();
                                break;
                            }
                            Course course = new OnlineCourse(name, credits, department, url);
                            boolean result = courseDAO.addCourse(course);
                            oos.writeBoolean(result);
                            oos.flush();
                            break;
                        case 3: //get all courses
                            List<Course> courseList = courseDAO.getAllCourses();
                            oos.writeObject(courseList);
                            oos.flush();
                            break;
                        case 4: //find course by id
                            int courseId = dis.readInt();
                            Course course1 = courseDAO.getCourseById(courseId);
                            oos.writeObject(course1);
                            oos.flush();
                            break;
                        case 5: //update online course
                            int id1 = dis.readInt();
                            Course c = courseDAO.getCourseById(id1);
                            if (c==null){
                                oos.writeBoolean(false);
                                oos.flush();
                                break;
                            }
                            OnlineCourse course2 = (OnlineCourse) c;
                            String name1 = dis.readUTF();
                            int credits1 = dis.readInt();
                            String url1 = dis.readUTF();
                            int departmentId1 = dis.readInt();
                            Department department1 = departmentDAO.findById(departmentId1);
                            if(department1==null){
                                oos.writeBoolean(false);
                                oos.flush();
                                break;
                            }
                            course2.setTitle(name1);
                            course2.setCredits(credits1);
                            course2.setUrl(url1);
                            course2.setDepartment(department1);
                            boolean result1 = courseDAO.updateCourse(course2);
                            System.out.println("Course: "+course2);
                            oos.writeBoolean(result1);
                            oos.flush();
                            break;
                        case 6: //delete course
                            int id2 = dis.readInt();
                            boolean result2 = courseDAO.deleteCourse(id2);
                            oos.writeBoolean(result2);
                            oos.flush();
                            break;
                        case 7: //exists course
                            int id3 = dis.readInt();
                            boolean result3 = courseDAO.existsCourseById(id3);
                            oos.writeBoolean(result3);
                            oos.flush();
                            break;
                        case 8: //findCourseWithMaxCredits
                            List<Course> courseList1 = courseDAO.findCourseWithMaxCredits();
                            oos.writeObject(courseList1);
                            oos.flush();
                            break;
                        case 9://findAllStudent
                            List<Student> studentList =studentDAO.findAll();
                            oos.writeObject(studentList);
                            oos.flush();
                            break;
                        case 10://findByEnrollmentDateInYear
                            int year = dis.readInt();
                            List<Student> studentList1 = studentDAO.findStudentByEnrollmentDateInYear(year);
                            oos.writeObject(studentList1);
                            oos.flush();
                            break;
                        case 11: //findStudentsEnrolledInCourse
                            String title1 = dis.readUTF();
                            List<Student> studentList2 = studentDAO.findStudentsEnrolledInCourse(title1);
                            oos.writeObject(studentList2);
                            oos.flush();
                            break;
                        case 12: //findByEnrollmentDate
                            int year1 = dis.readInt();
                            int month = dis.readInt();
                            int day = dis.readInt();
                            LocalDateTime date = LocalDateTime.of(year1, month, day, 0, 0);
                            List<Student> studentList3 = studentDAO.findStudentByEnrollmentDate(date);
                            oos.writeObject(studentList3);
                            oos.flush();
                            break;
                        case 13: //find department by id
                            int id = dis.readInt();
                            Department depart = departmentDAO.findById(id);
                            oos.writeObject(depart);
                            oos.flush();
                            break;
                        case 14://add department
                            String na = dis.readUTF();
                            double budget = dis.readDouble();
                            int year2 = dis.readInt();
                            int month1 = dis.readInt();
                            int day1 = dis.readInt();
                            int admin = dis.readInt();
                            LocalDateTime date1 = LocalDateTime.of(year2, month1, day1, 0, 0);
                            Department department2 = new Department(na, budget, date1, admin);
                            boolean result4 = departmentDAO.addDepartment(department2);
                            oos.writeBoolean(result4);
                            oos.flush();
                            break;
                        case 15: //find department with budget greater than threshold
                            double threshold = dis.readDouble();
                            List<Department> departmentList = departmentDAO.findDepartmentsWithBudgetGreaterThanThreshold(threshold);
                            oos.writeObject(departmentList);
                            oos.flush();
                            break;
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
