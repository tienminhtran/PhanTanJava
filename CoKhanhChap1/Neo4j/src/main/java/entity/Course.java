/*
 * @(#)Course.java 1.0  21/2/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     21/2/2024
 *@Version:  1.0
 */


package entity;

public class Course {
    private String courseID;
    private String name;


    private int hours;
    private Department department;

    public Course() {
    }

    public Course(String courseID, String name, int hours, Department department) {
        this.courseID = courseID;
        this.name = name;
        this.hours = hours;
        this.department = department;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
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
                "courseID='" + courseID + '\'' +
                ", name='" + name + '\'' +
                ", hours=" + hours +
                ", department=" + department+
                '}';
    }
}
