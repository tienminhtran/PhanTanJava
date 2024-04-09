package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Course")
@NamedQueries({
        @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c"),
        @NamedQuery(name = "Course.findById", query = "SELECT c FROM Course c WHERE c.id = :id"),
        @NamedQuery(name = "Course.deleteId", query = "DELETE FROM Course c WHERE c.id = :id"),
        @NamedQuery(name = "Course.updateId", query = "UPDATE Course c SET c.credits = :credits, c.title = :title WHERE c.id = :id"),
        @NamedQuery(name = "Course.listDepartment", query =    "SELECT c FROM Course c WHERE c.department.id = :id"),

})

@Inheritance(strategy = InheritanceType.JOINED)
public class Course implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 9203191448471981077L;


	@Column(name = "Credits")
    protected int credits;


    @Id
    @Column(name = "CourseID")
    protected int courseID;


    @Column(name = "Title")
    protected String title;

    @ManyToOne
    @JoinColumn(name = "DepartmentID")
    protected Department department;



    public Course(int courseID, String title) {
        this.courseID = courseID;
        this.title = title;
    }

    public Course(int courseID) {
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return "Course{" +
                "credits=" + credits +
                ", courseID=" + courseID +
                ", title='" + title + '\'' +
                ", department=" + department.getId() +
                '}';
    }
}
