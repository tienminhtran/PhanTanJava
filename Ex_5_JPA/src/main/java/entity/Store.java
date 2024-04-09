package entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "stores")
public class Store {
    @Id
    @Column(name = "store_id")
    private int id;
    @Column(name = "store_name")
    private String name;

    @Embedded
    private Contact contact;

    @Embedded
    private Address address;

}
