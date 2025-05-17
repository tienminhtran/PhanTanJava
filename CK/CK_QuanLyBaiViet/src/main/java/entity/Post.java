package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post implements Serializable {
    @Id
    @Column(name = "post_id")
    private String id;

    private String title;

    @Column(name = "contents")
    private String content;

    private int views;

    private int likes;

    private int shares;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user;


    @Embedded
    private Approval approval;


    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;




}
