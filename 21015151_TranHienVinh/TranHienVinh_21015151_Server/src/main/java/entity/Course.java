/*
 * @ {#} Course.java   1.0     26/03/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   26/03/2024
 * @version:    1.0
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c"),
        @NamedQuery(name = "Course.findByTitle", query = "SELECT c FROM Course c WHERE c.title like :title"),
        @NamedQuery(name = "Course.existsById", query = "SELECT (count(c)>0) FROM Course c WHERE c.id = :id"),
//        @NamedQuery(name = "Course.findCourseWithMaxCredits", query = "SELECT c FROM Course c WHERE c.credits = (SELECT MAX(c.credits) FROM Course c)")
})
//@NamedNativeQueries({
//        @NamedNativeQuery(name = "Course.findCourseWithMaxCredits", query = "select * from Course c where c.credits>=(select max(credits) from Course)", resultClass = Course.class)
//})
@NamedNativeQueries({
        @NamedNativeQuery(name = "Course.findCourseWithMaxCredits", query = "select * from Course c where c.credits>=(select max(credits) from Course)")
})
public abstract class Course implements Serializable {
    private static final long serialVersionUID = 5987948159586722404L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CourseID")
    protected int id;
    @Column(name = "Title")
    protected String title;
    @Column(name = "Credits")
    protected int credits;
    //Tạo quan hệ n-1 với Department
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DepartmentID")
    protected Department department;

    //Tạo quan hệ n-n với Person, tạo bảng mới CourseInstructor
    @ManyToMany
    @JoinTable(
            name = "CourseInstructor",
            joinColumns = @JoinColumn(name = "CourseID"),
            inverseJoinColumns = @JoinColumn(name = "PersonID")
    )
    protected Set<Instructor> instructors;

    public Course() {
    }

    public Course(String title, int credits, Department department) {
        this.title = title;
        this.credits = credits;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", credits=" + credits +
                '}';
    }
}
