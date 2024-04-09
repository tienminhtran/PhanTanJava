package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "OnlineCourse")
public class OnlineCourse extends Course implements Serializable {

    @Column(name = "URL")
    private String url;

    public OnlineCourse(int courseID, String title, String url) {
        super(courseID, title);
        this.url = url;
    }
}
