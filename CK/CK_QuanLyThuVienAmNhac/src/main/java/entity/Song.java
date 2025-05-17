package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "songs")
@ToString

public class Song {
    @Id
    @Column(name = "song_id")
    private String id;
    private String name;

    private String runtime;

    private String lyric;

    @Column(name = "file_link")
    private String filelink;






}
