/*
 * @ {#} StudentGrade.java   1.0     26/03/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package entity;

import jakarta.persistence.*;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   26/03/2024
 * @version:    1.0
 */
@Entity
public class StudentGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollmentID")
    private int id;
    @Column(name = "Grade")
    private double grade;
    //Tạo mối quan hệ n - 1 với Student
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StudentID")
    private Student student;

    //Tạo mối quan hệ n - 1 với Course
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CourseID",
            foreignKey = @ForeignKey(name = "FK_StudentGrade_Course",
                    foreignKeyDefinition = "FOREIGN KEY (CourseID) REFERENCES Course(CourseID) ON DELETE CASCADE ON UPDATE CASCADE"
            )
    )
    private Course course;

    public StudentGrade() {
    }

    public StudentGrade(double grade, Student student, Course course) {
        this.grade = grade;
        this.student = student;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "StudentGrade{" +
                "id=" + id +
                ", grade=" + grade +
                ", student=" + student +
                ", course=" + course +
                '}';
    }
}
