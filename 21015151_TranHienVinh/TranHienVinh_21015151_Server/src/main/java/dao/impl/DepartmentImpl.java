/*
 * @ {#} DepartmentImpl.java   1.0     04/04/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package dao.impl;

import dao.DepartmentDAO;
import entity.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   04/04/2024
 * @version:    1.0
 */
public class DepartmentImpl implements DepartmentDAO {
    private EntityManager em;

    public DepartmentImpl() {
        em = Persistence.createEntityManagerFactory("JPA_MSSQL").createEntityManager();
    }

    @Override
    public List<Department> findAll() {
        return null;
    }

    @Override
    public Department findById(int departmentId) {
        if (em.find(Department.class, departmentId) != null) {
            return em.find(Department.class, departmentId);
        }else {
            return null;
        }
    }

    @Override
    public boolean addDepartment(Department department) {
        try {
            em.getTransaction().begin();
            em.persist(department);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public List<Department> findDepartmentsWithBudgetGreaterThanThreshold(double budget) {
       return em.createNamedQuery("Department.findDepartmentsWithBudgetGreaterThanThreshold", Department.class)
               .setParameter("budget", budget)
               .getResultList();
    }
}
