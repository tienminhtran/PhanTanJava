/*
 * @ {#} CourseDAO.java   1.0     02/04/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package dao;

import entity.Course;

import java.util.List;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   02/04/2024
 * @version:    1.0
 */
public interface CourseDAO {
    public boolean addCourse(Course course);
    public boolean updateCourse(Course course);
    public boolean deleteCourse(int courseId);
    public Course getCourseById(int courseId);
    public List<Course> getAllCourses();
    public List<Course> findCourseByTitle(String title);
    public Course findCourseByTitle2(String title);
    public boolean existsCourseById(int courseId);
    public List<Course> findCourseWithMaxCredits();
}
