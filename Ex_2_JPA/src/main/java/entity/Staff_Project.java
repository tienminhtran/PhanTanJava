package entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "staff_project")
public class Staff_Project {
    @Id
    @ManyToOne
    @JoinColumn(name = "staff_id") // join be6n group
    private Staff staff;

    @Id
    @ManyToOne
    @JoinColumn(name = "project_id") // join be6n group
    private Project project;

}
