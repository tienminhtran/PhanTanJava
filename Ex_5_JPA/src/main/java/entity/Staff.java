package entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "staffs")
@AttributeOverride(name="id", column=@Column(name="staff_id"))
public class Staff extends Person{
    @Column(columnDefinition = "TINYINT")
    private byte active;


    // nó quay về chính nó
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Staff manager;


    // quan hệ ới store

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
    public Staff(int id, String firstName, String lastName, Contact contact, byte active) {
        super(id, firstName, lastName, contact);
        this.active = active;
    }

    public Staff(int id, String firstName, String lastName, Contact contact, byte active, Staff manager, Store store) {
        super(id, firstName, lastName, contact);
        this.active = active;
        this.manager = manager;
        this.store = store;
    }
}
