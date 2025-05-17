package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "positions")
@ToString

public class Position {

    @Id
    @Column(name = "position_id")
    private String id;

    private String name;


    private String description;


    private double salary;

    private int number;


}
