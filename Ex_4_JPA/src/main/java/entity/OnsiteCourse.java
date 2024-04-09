package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "OnsiteCourse")
public class OnsiteCourse extends Course implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6814208710263074852L;

	@Column(name = "Days")
    private String day;

    @Column(name = "Location")
    private String location;

    @Column(name = "Time")
    private LocalDateTime time;


    public OnsiteCourse(int credits, int courseID, String title, Department department, String day, String location, LocalDateTime time) {
        super(credits, courseID, title, department);
        this.day = day;
        this.location = location;
        this.time = time;
    }

    public OnsiteCourse(int courseID, String title, String day, String location, LocalDateTime time) {
        super(courseID, title);
        this.day = day;
        this.location = location;
        this.time = time;
    }

    @Override
    public String toString() {
        return "OnsiteCourse{" +
                "day='" + day + '\'' +
                ", location='" + location + '\'' +
                ", time=" + time +
                ", credits=" + credits +
                ", courseID=" + courseID +
                ", title='" + title + '\'' +
                ", department=" + department +
                '}';
    }
}
