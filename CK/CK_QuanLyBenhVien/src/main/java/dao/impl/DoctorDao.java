package dao.impl;

import dao.ImplDoctorDao;
import entity.Doctor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorDao implements ImplDoctorDao {

    final EntityManager em;

    final EntityTransaction et;

    public DoctorDao() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ck_quanlybenhvien");
        this.em =  emf.createEntityManager();
        this.et = em.getTransaction();
    }

    @Override
    public List<Doctor> getDoctorByDepartment(String deptName) {
        String sql = "Select do \n" +
                "from Treatment t\n" +
                "join Department de\n" +
                "on t.department.id = de.id\n" +
                "join Doctor do\n" +
                "on t.doctor.id = do.id\n" +
                "where de.name = :deptName";
        try{
            et.begin();
            List<Doctor> doctors = em.createQuery(sql, Doctor.class)
                    .setParameter("deptName", deptName)
                    .getResultList();
            et.commit();
            return doctors;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;


    }

    @Override
    public Map<Doctor, Integer> getNoTreatmentDoctor(int month, int year) {
        String sql = "\n" +
                "Select do.id,count(*) as tong\n" +
                "from Treatment t\n" +
                "join Doctor do\n" +
                "on t.doctor.id = do.id\n" +
                "where year(t.startDate)=:year and month(t.startDate)= :month\n" +
                "group by do.id, do.name, do.phone, do.speciality";
        try{
            et.begin();
            Map<Doctor, Integer> map = new HashMap<>();
            List<Object[]> result = em.createQuery(sql)
                    .setParameter("year", year)
                    .setParameter("month", month)
                    .getResultList();
            for (Object[] objects : result){
                Doctor doctor = em.find(Doctor.class, objects[0]);
                Integer count = Integer.parseInt(objects[1].toString());
                map.put(doctor, count);
            }
            et.commit();
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
