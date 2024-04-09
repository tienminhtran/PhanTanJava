package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "order_id")
    private int id;

    @Column(name = "order_status")
    private byte orderStatus;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "shipped_date")
    private LocalDate shippedDate;

    @Column(name = "required_date")
    private LocalDate requireddate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;


}
