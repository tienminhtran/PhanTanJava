package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "salaried employees")
public class SalriedEmployee  extends Employee{

    @Column(columnDefinition = "float")
    private double weeklySalary;


    public SalriedEmployee(int emp_id, String first_name, String last_name, String socialSecuityNumber, double weeklySalary) {
        super(emp_id, first_name, last_name, socialSecuityNumber);
        this.weeklySalary = weeklySalary;
    }
    public double earnings() {
        return weeklySalary;
    }




}

