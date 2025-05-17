package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bills")
public class Bill implements Serializable{


    private int days;

    @Column(name = "service_fee")
    private double servicefee;

    @Column(name = "total_amount")
    private double totalamount;

    @OneToOne
    @Id
    @JoinColumn(name = "bill_id")
    private Booking booking;
}
