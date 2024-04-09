package dao;

import entity.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class DepartmentDao extends GenericDao<Department> {
    private EntityManager em;

    public DepartmentDao() {
        em = Persistence.createEntityManagerFactory("ex4")
                .createEntityManager();
    }
    public DepartmentDao(EntityManager em) {
        this.em = em;
    }

    public boolean addDepartment(Department department) {
        return add(department);
    }

    public boolean updateDepartment(Department department) {
        return update(department);
    }


    public boolean deleteDepartment(Department department) {
        return delete(department);
    }

    public List<Department> getAllDepartmentsById(int ma) {
        return em.createNamedQuery("Department.findById", Department.class)
                .setParameter("id", ma)
                .getResultList();

    }

    public List<Department> getAllDepartments() {
        return em.createNamedQuery("Department.findAll", Department.class)
                .getResultList();
    }

    public boolean deleteDepartmentById(int departmentId) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.createNamedQuery("Department.deleteId")
                    .setParameter("id", departmentId)
                    .executeUpdate();
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            return false;
        }
    }

    public boolean updateId(int departmentId, Department department) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.createNamedQuery("Department.updateId")
                    .setParameter("id", departmentId)
                    .setParameter("budget", department.getBudget())
                    .setParameter("name", department.getName())
                    .setParameter("startDate", department.getStartDate())
                    .setParameter("administrator", department.getAdministrator())
                    .executeUpdate();
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            return false;
        }
    }




}
