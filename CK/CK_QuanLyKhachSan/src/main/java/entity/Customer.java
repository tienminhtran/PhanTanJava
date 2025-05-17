package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "customers")
public class Customer implements Serializable {

    @Id
    @Column(name = "customer_id")
    private String id;

    @Column(name = "customer_name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;
}
