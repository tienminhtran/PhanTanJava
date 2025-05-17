package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "albums")
@ToString
public class Album {
    @Id
    @Column(name = "album_id")
    private String id;

    private String title;


    private double price;

    @Column(name = "year_of_release")
    private int yearOfRelease;

    @Column(name = "download_link")
    private String downloadLink;


    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

}
