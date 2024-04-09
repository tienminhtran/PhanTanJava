package entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Inheritance(strategy = InheritanceType. SINGLE_TABLE)
public abstract class Person {

    @Id
    private int id;

    @Column(name = "first_name")
    private String firstName;


    @Column(name = "last_name")
    private String lastName;

    @Embedded
    private Contact contact;


}
