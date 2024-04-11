/*
 * @ {#} StudentDAO.java   1.0     07/04/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package dao;

import entity.Student;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   07/04/2024
 * @version:    1.0
 */
public interface StudentDAO {
    public List<Student> findAll();
    public List<Student> findStudentByEnrollmentDateInYear(int year);
    public List<Student> findStudentByEnrollmentDate(LocalDateTime date);
    public List<Student> findStudentsEnrolledInCourse(String title);
}
