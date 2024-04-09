package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "courses")
@AllArgsConstructor
public class Course {
	@Id
	@Column(name="course_id")
	private String id;
	@Column(columnDefinition = "nvarchar(100)", unique = true, nullable = false)
	private String name;
	private int credits;
	
}
