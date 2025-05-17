package entity;

import jakarta.persistence.*;
import lombok.*;
import org.mariadb.jdbc.plugin.codec.LocalDateCodec;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Treatment implements Serializable {

    @Id
    private LocalDate startDate;

    private LocalDate endDate;

    private String diagnosis;

    @ManyToOne
    @Id
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @Id
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public Treatment(LocalDate startDate, LocalDate endDate, String diagnosis, Department department, Patient patient, Doctor doctor) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.diagnosis = diagnosis;
        this.department = department;
        this.patient = patient;
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Treatment{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", diagnosis='" + diagnosis + '\'' +
                ", department=" + department +
                ", patient=" + patient +
                ", doctor=" + doctor +
                '}';
    }
}
