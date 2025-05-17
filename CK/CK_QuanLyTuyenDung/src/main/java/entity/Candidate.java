package entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidates")
@ToString
public class Candidate {

    @Id
    @Column(name = "candidate_id")
    private String id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "year_of_birth")
    private int yearOfBirth;


    private String gender;
    private String email;
    private String phone;


    private String description;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;
}
