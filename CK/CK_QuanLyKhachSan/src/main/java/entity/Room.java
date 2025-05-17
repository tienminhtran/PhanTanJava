package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "rooms")
public class Room implements Serializable {
    @Id
    @Column(name = "room_id")
    private String id;

    private String description;

    private String type;

    @Column(name = "bed_option")
    private String bedOption;

    @Column(name = "sleeps_count")
    private int sleepsCount;

    @Column(name = "smoking_allowed")
    private boolean smokingAllowed;

    private double price;
}
