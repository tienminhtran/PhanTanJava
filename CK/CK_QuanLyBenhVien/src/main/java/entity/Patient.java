package entity;

import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Patient extends Person implements Serializable {
    private String gender;

    private LocalDate dateOfBirth;

    private String address;

    public Patient(String id, String name, String phone, String gender, LocalDate dateOfBirth, String address) {
        super(id, name, phone);
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public Patient(String gender, LocalDate dateOfBirth, String address) {
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                '}';
    }
}
