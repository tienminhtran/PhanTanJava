package entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Employee {

    @Id
    @Column(name = "emp_id")
    protected  int emp_id;
    @Column(name = "first_name")
    protected  String first_name;
    @Column(name = "last_name")
    protected  String last_name;
    @Column(name = "ssn", nullable = false)
    protected  String socialSecuityNumber;

    public abstract double earnings();

}
