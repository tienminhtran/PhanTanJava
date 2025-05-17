package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment implements Serializable {

    private String contents;

    private int likes;
    @Id
    @Column(name = "comment_date")
    private LocalDateTime commentDate;

    @ManyToOne
    @Id
    @JoinColumn(name = "user_name")
    private User user;

    @ManyToOne
    @Id
    @JoinColumn(name = "post_id")
    private Post post;
}
