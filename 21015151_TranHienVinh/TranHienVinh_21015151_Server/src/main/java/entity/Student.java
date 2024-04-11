/*
 * @ {#} Student.java   1.0     26/03/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   26/03/2024
 * @version:    1.0
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
        @NamedQuery(name = "Student.findByEnrollmentDateInYear", query = "SELECT s FROM Student s WHERE year(s.enrollmentDate) = :year"),
        @NamedQuery(name = "Student.findByEnrollmentDate", query = "SELECT s FROM Student s WHERE s.enrollmentDate = :date"),
        @NamedQuery(name="Student.findStudentsEnrolledInCourse", query = "SELECT s FROM Student s inner join s.studentGrades sg WHERE sg.course.title like :title")
})
public class Student extends Person implements Serializable {
    @Column(name = "EnrollmentDate")
    private LocalDateTime enrollmentDate;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<StudentGrade> studentGrades;
    public Student() {
    }

    public Student(String lastName, String firstName, LocalDateTime enrollmentDate) {
        super(lastName, firstName);
        this.enrollmentDate = enrollmentDate;
    }

    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDateTime enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "enrollmentDate=" + enrollmentDate +
                ", id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
