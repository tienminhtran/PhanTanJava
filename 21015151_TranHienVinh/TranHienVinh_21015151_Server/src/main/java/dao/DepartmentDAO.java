/*
 * @ {#} DepartmentDAO.java   1.0     04/04/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package dao;

import entity.Department;

import java.util.List;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   04/04/2024
 * @version:    1.0
 */
public interface DepartmentDAO {
    public List<Department> findAll();
    public Department findById(int id);
    public boolean addDepartment(Department department);
    public List<Department> findDepartmentsWithBudgetGreaterThanThreshold(double budget);
}
