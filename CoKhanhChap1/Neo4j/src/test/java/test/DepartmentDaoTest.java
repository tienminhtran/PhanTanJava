package test;

import dao.DepartmentDao;
import entity.Department;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DepartmentDaoTest {

    private DepartmentDao departmentDao;

    @BeforeAll
    public void init() {
        departmentDao = new DepartmentDao("coursedb");
    }

    @Test
    public void testUpDateName() {
        departmentDao.upDateName("Math", "bbb");
    }

    @Test
    public void testUpDateDepartment() {
        departmentDao.upDateDepartment(new Department("Math", "qqqq", "Carson", "USA", null));
    }

    @Test
    public void testDanhSachKhoa() {
        System.out.println(departmentDao.danhSachKhoa());
    }

    @Test
    public void testDanhSachCacTruongKhoa() {
        System.out.println(departmentDao.danhSachCacTruongKhoa());
    }

    @Test
    public void testDanhSachKhoaHocTheoKhoa() {
        System.out.println(departmentDao.danhSachCacTruongKhoa_maKhoa());
    }
    @Test
    public void testDanhSachCacTruongKhoa_TheoMa() {
        System.out.println(departmentDao.danhSachCacTruongKhoa_TheoMa("CS"));
    }

    @AfterAll
    public void close() {
        departmentDao.close();
    }
}
