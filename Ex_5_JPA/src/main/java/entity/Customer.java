package entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
@AttributeOverride(name="id", column=@Column(name="customer_id"))

public class Customer extends Person{

    @Embedded
    private Address address;

    public Customer(int id, String firstName, String lastName, Contact contact, Address address) {
        super(id, firstName, lastName, contact);
        this.address = address;
    }

}
