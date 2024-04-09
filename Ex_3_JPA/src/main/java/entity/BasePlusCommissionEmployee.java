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
@Table(name = "base plus commission employee")
public class BasePlusCommissionEmployee extends CommissionEmployees{
    private double baseSalary;

    public BasePlusCommissionEmployee(int emp_id, String first_name, String last_name, String socialSecuityNumber, double grossSales, double commissionRate, double baseSalary) {
        super(emp_id, first_name, last_name, socialSecuityNumber, grossSales, commissionRate);
        this.baseSalary = baseSalary;
    }

    public double earnings() {
        return baseSalary + super.earnings();
    }
}
