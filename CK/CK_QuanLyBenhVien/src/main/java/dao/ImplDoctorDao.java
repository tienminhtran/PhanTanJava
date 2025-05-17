package dao;

import entity.Doctor;

import java.util.List;
import java.util.Map;

public interface ImplDoctorDao {

    public List<Doctor> getDoctorByDepartment(String deptName);

    public Map<Doctor, Integer> getNoTreatmentDoctor(int month, int year);
}
