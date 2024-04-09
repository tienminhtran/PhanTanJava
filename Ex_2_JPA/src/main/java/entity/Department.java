package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "departments")

public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dept_id;
    private String location;
    private String dept_name;

    @OneToMany(mappedBy = "department")
    private List<Staff> staffs;

    public Department(String location, String dept_name) {
        this.location = location;
        this.dept_name = dept_name;
    }
}
