package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "hour employees")
public class HourlEmployee extends Employee{
    private double wage;
    private double hours;

    public HourlEmployee(int emp_id, String first_name, String last_name, String socialSecuityNumber, double wage, double hours) {
        super(emp_id, first_name, last_name, socialSecuityNumber);
        this.wage = wage;
        this.hours = hours;
    }

    public double earnings() {
        return wage * hours;
    }
}
