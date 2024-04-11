/*
 * @ {#} Main.java   1.0     26/03/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package test;

import dao.CourseDAO;
import dao.DepartmentDAO;
import dao.StudentDAO;
import dao.impl.CourseImpl;
import dao.impl.DepartmentImpl;
import dao.impl.StudentImpl;
import entity.Course;
import entity.Department;
import entity.Student;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   26/03/2024
 * @version:    1.0
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_MSSQL");
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        try {
//            tx.begin();
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//            e.printStackTrace();
//        }
//        CourseDAO courseDAO = new CourseImpl();
//        List<Course> courses = courseDAO.findCourseWithMaxCredits();
//        for (Course course: courses){
//            System.out.println(course);
//        }
        DepartmentDAO departmentDAO = new DepartmentImpl();
        List<Department> departments = departmentDAO.findDepartmentsWithBudgetGreaterThanThreshold(200000);
        for (Department department: departments){
            System.out.println(department);
        }
//        StudentDAO studentDAO = new StudentImpl();
//        LocalDate date= LocalDate.of(2002,9,1);
//        LocalDateTime date1 = date.atTime(LocalTime.MIN);
//        List<Student> students = studentDAO.findStudentByEnrollmentDate(date1);
//        for (Student student: students){
//            System.out.println(student);
//        }
//        EntityManager entityManager = emf.createEntityManager();
//        entityManager.getTransaction().begin();
//
//        List<Course> coursesWithMaxCredits = entityManager.createNamedQuery("Course.findCourseWithMaxCredits", Course.class)
//                        .getResultStream().collect(Collectors.toList());
//
//        entityManager.getTransaction().commit();
//        entityManager.close();
//
//        for (Course course : coursesWithMaxCredits) {
//            System.out.println(course);
//        }
    }
}
