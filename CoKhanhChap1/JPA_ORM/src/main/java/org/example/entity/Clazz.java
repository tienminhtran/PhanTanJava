package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "classes")
public class Clazz {
	@Id
	@Column(name="class_id")
	private String id;
	@Column(columnDefinition = "nvarchar(100)", unique = true, nullable = false)
	private String name;
	private int noOfStudents;
	
	@OneToMany(mappedBy = "clazz")
	@ToString.Exclude
	private List<Student> students;

	public Clazz(String id, String name, int noOfStudents) {
		super();
		this.id = id;
		this.name = name;
		this.noOfStudents = noOfStudents;
	}
}
