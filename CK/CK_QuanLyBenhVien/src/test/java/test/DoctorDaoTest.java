package test;

import dao.impl.DoctorDao;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DoctorDaoTest {

    private DoctorDao doctorDao;

    @BeforeAll
    public void init() {
        doctorDao = new DoctorDao();
    }

    @Test
    public void getDoctorByDepartment() {
        doctorDao.getDoctorByDepartment("Dermatology").forEach(System.out::println);
    }


    @Test
    public void getNoTreatmentDoctor() {
        doctorDao.getNoTreatmentDoctor(4, 2023).forEach((k, v) -> System.out.println(k + " " + v));
    }

    @AfterAll
    public void destroy() {
        doctorDao = null;
    }
}
