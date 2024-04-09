package test;

import dao.StudentDao;
import entity.Student;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentDaoTest {
    private StudentDao studentDao;

    @BeforeAll
    public void init() {
        studentDao = new StudentDao("coursedb");
    }


    @Test
    public void testDanhSachSinhVien() {
        List<Student> students = studentDao.danhSachSV(5);
        System.out.println(students);

    }


    @Test
    public void testFindSVTheoMa() {
        Student student = studentDao.findStudentById("11");
        System.out.println(student);
    }


    @AfterAll
    public void close() {
        studentDao.close();
    }


}
