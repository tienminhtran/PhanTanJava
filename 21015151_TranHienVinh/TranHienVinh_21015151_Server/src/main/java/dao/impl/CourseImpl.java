/*
 * @ {#} CourseImpl.java   1.0     02/04/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package dao.impl;

import dao.CourseDAO;
import entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   02/04/2024
 * @version:    1.0
 */
public class CourseImpl implements CourseDAO {
    private EntityManager em;

    public CourseImpl() {
        em= Persistence.createEntityManagerFactory("JPA_MSSQL").createEntityManager();
    }
    @Override
    public boolean addCourse(Course course) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(course);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCourse(Course course) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(course);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCourse(int courseId) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Course course = em.find(Course.class, courseId);
//            em.detach(course);
            em.remove(course);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Course getCourseById(int courseId) {
        return Optional.ofNullable(em.find(Course.class, courseId)).orElse(null);
    }

    @Override
    public List<Course> getAllCourses() {
//        chưa soạn
//        return em.createQuery("SELECT c FROM Course c", Course.class).getSingleResult();
//        soạn trước
        return em.createNamedQuery("Course.findAll", Course.class).getResultList();
    }

    @Override
    public List<Course> findCourseByTitle(String title) {
        return em.createNamedQuery("Course.findByTitle", Course.class)
                .setParameter("title", "%"+title+"%").getResultList();
    }

    @Override
    public Course findCourseByTitle2(String title) {
        return em.createQuery("SELECT c FROM Course c WHERE c.title = :title", Course.class)
                .setParameter("title", title)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existsCourseById(int courseId) {
        return em.createNamedQuery("Course.existsById", Boolean.class)
                .setParameter("id", courseId).getSingleResult();
    }

    @Override
    public List<Course> findCourseWithMaxCredits() {
        String sqlQuery = "select * from Course c where c.credits>=(select max(credits) from Course)";
        Query query = em.createNativeQuery(sqlQuery, Course.class);
        return query.getResultList();
    }
}
