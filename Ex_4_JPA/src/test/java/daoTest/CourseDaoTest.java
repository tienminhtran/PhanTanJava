package daoTest;

import dao.CourseDao;
import dao.DepartmentDao;
import entity.Course;
import entity.Department;
import jakarta.persistence.*;
import org.junit.jupiter.api.*;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CourseDaoTest {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex4");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    private CourseDao courseDao;

    @BeforeAll
    public void setUp() {
        courseDao = new CourseDao(em);
    }
    @Test
    public void testAddCourse() {
        Department department = new Department();
        department.setId(13);

        Course course = new Course();
        course.setDepartment(department);
        course.setCourseID(1);
        course.setTitle("MongoDB");
        course.setCredits(3);
        boolean checked = courseDao.addCourse(course);
        Assertions.assertTrue(checked);

    }
    @Test
    public void getAllCoursesByDepartmentId() {
        List<Course> courses = courseDao.getAllCoursesByDepartmentId(1);
        System.out.println(courses);

    }




    @AfterAll
    public void tearDown() {
        em.close();
        emf.close();
    }


}
