/*
 * @ {#} Instructor.java   1.0     26/03/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   26/03/2024
 * @version:    1.0
 */
@Entity
public class Instructor extends Person{
    @Column(name = "HireDate")
    private LocalDateTime hireDate;

    public Instructor() {
    }

    public Instructor(String lastName, String firstName, LocalDateTime hireDate) {
        super(lastName, firstName);
        this.hireDate = hireDate;
    }

    public LocalDateTime getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDateTime hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "hireDate=" + hireDate +
                ", id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
