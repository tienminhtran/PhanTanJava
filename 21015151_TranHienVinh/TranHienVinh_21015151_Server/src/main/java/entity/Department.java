/*
 * @ {#} Department.java   1.0     26/03/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   26/03/2024
 * @version:    1.0
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d"),
        @NamedQuery(name = "Department.findById", query = "SELECT d FROM Department d WHERE d.id = :id"),
        @NamedQuery(name = "Department.findDepartmentsWithBudgetGreaterThanThreshold", query = "SELECT d FROM Department d WHERE d.budget >= :budget")
})
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DepartmentID")
    private int id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Budget")
    private double budget;
    @Column(name = "StartDate")
    private LocalDateTime startDate;
    @Column(name = "Administrator")
    private int administator;

    public Department() {
    }

    public Department(String name, double budget, LocalDateTime startDate, int administator) {
        this.name = name;
        this.budget = budget;
        this.startDate = startDate;
        this.administator = administator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public int getAdministator() {
        return administator;
    }

    public void setAdministator(int administator) {
        this.administator = administator;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", startDate=" + startDate +
                ", administator=" + administator +
                '}';
    }
}
