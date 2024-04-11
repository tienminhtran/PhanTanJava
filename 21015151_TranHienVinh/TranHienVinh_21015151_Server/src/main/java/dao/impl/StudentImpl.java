/*
 * @ {#} StudentImpl.java   1.0     07/04/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package dao.impl;

import dao.StudentDAO;
import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   07/04/2024
 * @version:    1.0
 */
public class StudentImpl implements StudentDAO {
    private EntityManager em;

    public StudentImpl() {
        em = Persistence.createEntityManagerFactory("JPA_MSSQL").createEntityManager();
    }
    @Override
    public List<Student> findAll() {
        return
                em.createNamedQuery("Student.findAll", Student.class).getResultList();
    }

    @Override
    public List<Student> findStudentByEnrollmentDateInYear(int year) {
        return em.createNamedQuery("Student.findByEnrollmentDateInYear", Student.class)
                .setParameter("year", year)
                .getResultList();
    }
    @Override
    public List<Student> findStudentByEnrollmentDate(LocalDateTime date) {
        return em.createNamedQuery("Student.findByEnrollmentDate", Student.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Student> findStudentsEnrolledInCourse(String title) {
        return em.createNamedQuery("Student.findStudentsEnrolledInCourse", Student.class)
                .setParameter("title", "%"+title+"%")
                .getResultList();
    }
}
