package dao;

import entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class CourseDao extends GenericDao<Course>{
    private EntityManager em;

    public CourseDao() {
        em = Persistence.createEntityManagerFactory("ex4")
                .createEntityManager();
    }

    public CourseDao(EntityManager em) {
        this.em = em;
    }

    public boolean addCourse(Course course) {
        return add(course);
    }
    public boolean updateCourse(Course course) {
        return update(course);
    }
    public boolean deleteCourse(Course course) {
        return delete(course);
    }
    public Course getCourseById(int id) {
        return em.createNamedQuery("Course.findById", Course.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    public List<Course> getAllCourses() {
        return em.createNamedQuery("Course.findAll", Course.class)
                .getResultList();
    }
    public boolean deleteCourseById(int courseId) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.createNamedQuery("Course.deleteId")
                    .setParameter("id", courseId)
                    .executeUpdate();
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            return false;
        }
    }

    public List<Course> getAllCoursesByDepartmentId(int departmentId) {
        return em.createNamedQuery("Course.listDepartment", Course.class)
                .setParameter("id", departmentId)
                .getResultList();
    }


}
