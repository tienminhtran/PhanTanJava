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
@Table(name = "bookings_rooms")
public class BookingRoom  implements Serializable {
    @ManyToOne
    @Id
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne
    @Id
    @JoinColumn(name = "room_id")
    private Room room;

}
