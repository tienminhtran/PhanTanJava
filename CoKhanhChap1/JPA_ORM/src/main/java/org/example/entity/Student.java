package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;


@Setter
@Getter
@ToString
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {
	@Id
	@Column(name="student_id")
	private String id;
	@Column(columnDefinition = "nvarchar(100)", nullable = false)
	private String name;
	@Column(unique = true, nullable = false)
	private String email;
	private LocalDate dob;
	
	@ManyToOne
	@JoinColumn(name="class_id")
	private Clazz clazz;
	
//	public Student() {
//	}
//
	public Student(String id, String name, String email, LocalDate dob) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.dob = dob;
	}
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public LocalDate getDob() {
//		return dob;
//	}
//
//	public void setDob(LocalDate dob) {
//		this.dob = dob;
//	}
//
//	@Override
//	public String toString() {
//		return "Student [id=" + id + ", name=" + name + ", email=" + email + ", dob=" + dob + "]";
//	}
//	
//	
}
