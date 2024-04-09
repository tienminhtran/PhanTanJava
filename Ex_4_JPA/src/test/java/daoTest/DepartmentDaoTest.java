package daoTest;

import dao.DepartmentDao;
import dao.DepartmentDemMoDao;
import entity.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DepartmentDaoTest {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex4");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    private DepartmentDao departmentDao;

    @BeforeAll
    public void setUp() {
        departmentDao = new DepartmentDao(em);
    }

    @Test
    public void testAddDepartment() {
        Department department = new Department();
        department.setBudget(243);
        department.setName("MongoDB Department");
        department.setStartDate(java.time.LocalDate.now());
        department.setAdministrator(1);
        boolean checked = departmentDao.addDepartment(department);
        Assertions.assertTrue(checked);
    }

    @Test
    public void testUpdateDepartment() {
        Department department = new Department();
        department.setBudget(243);
        department.setName("MongoDB Department");
        department.setStartDate(java.time.LocalDate.now());
        department.setAdministrator(1);
        boolean checked = departmentDao.addDepartment(department);
        Assertions.assertTrue(checked);
    }

    @Test
    public void testDeleteDepartment() {
        Department department = new Department();
        department.setBudget(243);
        department.setName("MongoDB Department");
        department.setStartDate(java.time.LocalDate.now());
        department.setAdministrator(1);
        boolean checked = departmentDao.deleteDepartment(department);
        Assertions.assertTrue(checked);
    }

    @Test
    public void getAllDepartmentsById() {
        DepartmentDao departmentDao = new DepartmentDao(em);

        System.out.println(departmentDao.getAllDepartmentsById(1));
    }

    @Test
    public void getAllDepartments() {
        DepartmentDao departmentDao = new DepartmentDao(em);
        for(Department department : departmentDao.getAllDepartments()) {
            System.out.println(department);
        }

    }
    @Test
    public void deleteDepartmentById() {
        DepartmentDao departmentDao = new DepartmentDao(em);
        boolean checked = departmentDao.deleteDepartmentById(13);
        Assertions.assertTrue(checked);
    }

    @Test
    public void updateId() {
        DepartmentDao departmentDao = new DepartmentDao(em);
        Department department = new Department();
        department.setBudget(200);
        department.setName("Python Department");
        department.setStartDate(java.time.LocalDate.of(2021, 3, 2));
        department.setAdministrator(8);

        boolean checked = departmentDao.updateId(8, department);
        Assertions.assertTrue(checked);
    }

    @AfterAll
    public void tearDown() {
        em.close();
        emf.close();
    }
}
