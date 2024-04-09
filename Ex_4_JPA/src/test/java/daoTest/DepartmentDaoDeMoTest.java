package daoTest;

import dao.DepartmentDemMoDao;
import entity.Department;
import jakarta.persistence.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DepartmentDaoDeMoTest {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex4");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    private DepartmentDemMoDao departmentDemMoDao;

    @BeforeAll
    public void setUp() {
        departmentDemMoDao = new DepartmentDemMoDao(em, tx);
    }


    @Test
    public void testAddDepartment() {
        Department department = new Department();
        department.setBudget(1000000);
        department.setName("Department of Computer Science");
        department.setStartDate(java.time.LocalDate.now());
        department.setAdministrator(1);
        boolean checked = departmentDemMoDao.addDepartment(department);
        Assertions.assertTrue(checked);
    }

    @Test
    public void testUpdateId() {
        Department department = new Department();
        department.setBudget(100);
        department.setName("Java Department");
        department.setStartDate(LocalDate.of(2021, 1, 1));
        department.setAdministrator(8);
        boolean checked = departmentDemMoDao.updateId(8, department);
        Assertions.assertTrue(checked);

    }

    @Test
    public void testDeleteId() {
        boolean checked = departmentDemMoDao.deleteId(10);
        Assertions.assertTrue(checked);
    }

    @Test
    public void testFindById() {
        Department department = departmentDemMoDao.findById(1);
        System.out.println(department);
        Assertions.assertNotNull(department);
    }
    @Test
    public void getAll() {
        for(Department department : departmentDemMoDao.getAll()) {
            System.out.println(department);
        }
    }
    @Test
    public void getAllById() {
//        getAllById
//        for(Department department : departmentDemMoDao.getAllById(LocalDate.of(2021, 1, 1)) {
//            System.out.println(department);
//        }

    }

    @AfterAll
    public void tearDown() {
        em.close();
        emf.close();
    }
}
