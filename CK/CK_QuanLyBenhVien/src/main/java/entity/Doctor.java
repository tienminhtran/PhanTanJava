package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Doctor extends Person implements Serializable {
    private String speciality;

    @Override
    public String toString() {
        return "Doctor{" +
                "speciality='" + speciality + '\'' +

                '}' + super.toString();
    }


    public Doctor(String id, String name, String phone, String speciality) {
        super(id, name, phone);
        this.speciality = speciality;
    }

    public Doctor(String speciality) {
        this.speciality = speciality;
    }
}
