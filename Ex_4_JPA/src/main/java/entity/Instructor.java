package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Instructor extends Person implements Serializable {

    @Column(name = "HireDate")
    private LocalDateTime hireDate;

    @ManyToMany
    @JoinTable(
            name = "CourseInstructor",
            joinColumns = @JoinColumn(name = "PersonID"),
            inverseJoinColumns = @JoinColumn(name = "CourseID")
    )
    protected Set<Course> courses;

    public Instructor(String firstName, String lastName, LocalDateTime hireDate) {
        super(firstName, lastName);
        this.hireDate = hireDate;
    }
}
