package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "student")
public class Student extends Person  {
    public Student(String firstName, String lastName) {
		super(firstName, lastName);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -8664888621257528975L;
	@Column(name = "EnrollmentDate")
    private LocalDate enrollmentDate;



}
