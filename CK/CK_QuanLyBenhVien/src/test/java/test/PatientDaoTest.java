package test;

import dao.impl.PatientDao;
import entity.Patient;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PatientDaoTest {

    private PatientDao patientDao;

    @BeforeAll
    public void init() {
        patientDao = new PatientDao();
    }

    @Test
    public void addPatient(){
        Patient p = new Patient();
        p.setId("001-10-0001");
        p.setName("Nguyen Van A");
        p.setPhone("0123456789");
        p.setAddress("HCM");
        p.setDateOfBirth(LocalDate.of(2000, 1, 1));
        p.setGender("Nam");
        patientDao.addPatient(p);
    }
    @Test
    public void addPatientTestFalse(){
        Patient p = new Patient();
        p.setId("001-1000-0001");
        p.setName("Nguyen Van A");
        p.setPhone("0123456789");
        p.setAddress("HCM");
        p.setDateOfBirth(LocalDate.of(2000, 1, 1));
        p.setGender("Nam");
        assertFalse(patientDao.addPatient(p));
    }

    @AfterAll
    public void destroy() {
        patientDao = null;
    }
}
