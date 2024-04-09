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
@Table(name = "commission employees")
public class CommissionEmployees extends Employee{
    private double grossSales;
    private double commissionRate;

    public CommissionEmployees(int emp_id, String first_name, String last_name, String socialSecuityNumber, double grossSales, double commissionRate) {
        super(emp_id, first_name, last_name, socialSecuityNumber);
        this.grossSales = grossSales;
        this.commissionRate = commissionRate;
    }

    public double earnings() {
        return grossSales * commissionRate;
    }

}
