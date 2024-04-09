package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "OfficeAssignment")
public class OfficeAssignment implements Serializable {

    @Column(name = "Location")
    private String location;

    @Column(name = "Timestamp")
    private Timestamp timestamp;

    @Id
    @OneToOne
    @JoinColumn(name = "InstructorID")
    private Instructor instructor;



}
