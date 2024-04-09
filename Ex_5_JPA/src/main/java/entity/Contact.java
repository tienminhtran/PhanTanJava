package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Embeddable

public class Contact {
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;

}

