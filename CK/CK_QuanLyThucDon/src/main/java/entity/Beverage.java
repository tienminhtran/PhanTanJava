package entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "beverages")
public class Beverage extends Item{

    @Enumerated(EnumType.STRING)
    private Size size;

    @Column(name = "supplier_name")
    private String supplierName;
}
