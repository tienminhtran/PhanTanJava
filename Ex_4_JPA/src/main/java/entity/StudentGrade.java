package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "StudentGrade")
//SELECT COUNT(*) AS TotalStudents
//FROM [dbo].[StudentGrade] s
//WHERE s.CourseID = 2021;
@NamedQuery(name = "StudentGrade.TotalStudents", query = "SELECT COUNT(s) AS TotalStudents FROM StudentGrade s WHERE s.course.courseID = :id")
public class StudentGrade implements Serializable {

    @Id
    @Column(name = "enrollmentID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enrollmentId;


    @Column(name = "Grade")
    private double grade;



    @ManyToOne
    @JoinColumn(name = "StudentID")
    private Student student;


    @ManyToOne
    @JoinColumn(name = "CourseID")
    private Course course;



    public StudentGrade(double grade, Student student) {
        this.grade = grade;
        this.student = student;
    }
}
