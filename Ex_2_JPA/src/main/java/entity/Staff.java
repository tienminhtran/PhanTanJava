package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@ToString
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "Staffs")

public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private int staff_id;
    private int age;
    private String staff_name;
    private String refer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    protected Department department;
//     thuoctinh da tri

    @ElementCollection
    @CollectionTable(name = "phones", joinColumns = @JoinColumn(name = "staff_id"))
    @Column(name = "number", nullable = false)
    protected Set<String> numbers;




    public Staff(int age, String staff_name, String refer) {
        this.age = age;
        this.staff_name = staff_name;
        this.refer = refer;
    }
}
