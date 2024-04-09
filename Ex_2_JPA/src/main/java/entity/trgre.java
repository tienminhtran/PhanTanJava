package entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode

@Table(name = "gfhhbfr")
public class trgre {


    @Id
    private int id;

    @Id
    @ManyToOne(
    @JoinColumn(name = "staff_id")
    private Staff staff;


    @EqualsAndHashCode.Exclude
    private String email;

    // Phone number field in the Phone entity

    public trgre(String email) {
        this.email = email;

    }
}
