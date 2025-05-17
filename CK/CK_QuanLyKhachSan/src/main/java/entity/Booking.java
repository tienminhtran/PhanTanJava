package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "bookings")
public class Booking implements Serializable {

    @Id
    @Column(name = "booking_id")
    private String id;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    private double deposit;

    private String note;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


}
