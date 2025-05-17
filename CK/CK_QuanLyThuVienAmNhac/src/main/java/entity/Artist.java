package entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "artists")
@ToString

public class Artist {

    @Id
    @Column(name = "artist_id")
    private String id;

    private String name;

    @Column(name= "birth_date")
    private LocalDate birthDate;

    private String url;


}
