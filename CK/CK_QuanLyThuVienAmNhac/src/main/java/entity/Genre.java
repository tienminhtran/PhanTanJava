package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "genres")
@ToString
public class Genre {
    @Id
    @Column(name = "genre_id")
    private String id;

    private String name;

    private String description;

}
