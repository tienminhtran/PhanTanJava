package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Department")

@NamedQueries(value = {
        @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d"),
        @NamedQuery(name = "Department.findById", query = "SELECT d FROM Department d WHERE d.id = :id"),
        @NamedQuery(name = "Department.deleteId", query = "DELETE FROM Department d WHERE d.id = :id"),
        @NamedQuery(name = "Department.updateId", query = "UPDATE Department d SET d.budget = :budget, d.name = :name, d.administrator = :administrator WHERE d.id = :id"),

})

public class Department implements Serializable {
    @Column(name = "Administrator")
    private int administrator;
    @Column(name = "Budget")
    private double budget;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DepartmentID")
    private int id;

    @Column(name = "Name")
    private String name;
    @Column(name = "StartDate")
    private LocalDate startDate;

}
