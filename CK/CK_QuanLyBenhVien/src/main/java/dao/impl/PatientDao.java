package dao.impl;

import dao.ImplPatientDao;
import entity.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class PatientDao implements ImplPatientDao {

    final EntityManager em;

    final EntityTransaction et;

    public PatientDao() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ck_quanlybenhvien");
        em = emf.createEntityManager();
        et = em.getTransaction();
    }

    @Override
    public boolean addPatient(Patient patient) {
        try{
            et.begin();
            if(patient.getId().matches("[0-9]{3}-[0-9]{2}-[0-9]{4}")) {
                em.persist(patient);
                et.commit();
                return true;
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }


}
