package test;

import dao.CDao;
import entity.Course;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CourseDaoTest {
    private CDao cDao;

    @BeforeAll
    void setUp() {
        cDao = new CDao("coursedb");

    }

    @Test
    public void testDanhSachKhoaHocTheoKhoa() {
        List<Course> courses = cDao.danhSachKhoaHocTheoKhoa("CS");
        System.out.println(courses);
    }

    @Test
    public void testAddCourse() {
        Course course = new Course("C01", "Java", 24, null);
        cDao.addCourse(course);
    }

    @Test
    public void testAddCourse2() {
        CDao cDao = new CDao("coursedb");
        System.out.println(cDao.danhSachKhoaHocKhoaCSandIE());

    }

    @AfterAll
    public void close() {
        cDao.close();
    }


}
