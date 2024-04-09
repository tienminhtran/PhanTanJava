package dao;

import entity.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.List;

public class DepartmentDemMoDao {
    private EntityManager em;
    private EntityTransaction tx;

    public DepartmentDemMoDao(EntityManager em, EntityTransaction tx) {
        this.em = em;
        this.tx = em.getTransaction();
    }

    /**
     * Insert into SanPham(MaSP,TenSP,Manhom) values(1,N‘Loa bluetooth’ , 1 )
     *
     * @param department
     * @return
     */
    public boolean addDepartment(Department department) {
        String query = "INSERT INTO Department(Administrator,Budget,Name,StartDate) VALUES (?, ?, ?, ?)";

        try {
            tx.begin();
            em.createNativeQuery(query)
                    .setParameter(1, department.getAdministrator())
                    .setParameter(2, department.getBudget())
                    .setParameter(3, department.getName())
                    .setParameter(4, department.getStartDate())
                    .executeUpdate();
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            return false;
        }
    }

    /**
     * UPDATE <table_name> SET <column_name> = value [, … ] [ WHERE <condition> ]
     * @param id
     * @param department
     * @return
     */
    public boolean updateId(int id, Department department) {
        String query = "UPDATE Department SET Administrator = ?, Budget = ?, Name = ?, StartDate = ? WHERE DepartmentID = ?";
        try {
            tx.begin();
            em.createNativeQuery(query)
                    .setParameter(1, department.getAdministrator())
                    .setParameter(2, department.getBudget())
                    .setParameter(3, department.getName())
                    .setParameter(4, department.getStartDate())
                    .setParameter(5, id)
                    .executeUpdate();
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            return false;
        }
    }
/**
 * UPDATE OBJECT
 */
    public boolean updateObject(Department department) {
        try {
            tx.begin();
            em.merge(department);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            return false;
        }
    }

    /**
     * DELETE FROM <table_name> WHERE <condition>
     */
    public boolean deleteId(int id) {
        String query = "DELETE FROM Department WHERE DepartmentID = ?";
        try {
            tx.begin();
            em.createNativeQuery(query)
                    .setParameter(1, id)
                    .executeUpdate();
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            return false;
        }
    }

    /**
     * SELECT * FROM <table_name> WHERE <condition>
     * @param id
     * @return
     */
    public Department findById(int id) {
        return em.find(Department.class, id);
    }

    public List<Department> getAll() {
//        return em.createQuery("SELECT d FROM Department d", Department.class)
////                .getResultList();
        String query = "SELECT * FROM Department";
        try {
            return em.createNativeQuery(query, Department.class)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Department> getAllById(LocalDate startDate) {
        String query = "SELECT * FROM Department WHERE DepartmentID = ?";
        try {
            return em.createNativeQuery(query, Department.class)
                    .setParameter(1, startDate)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }

    }

}
