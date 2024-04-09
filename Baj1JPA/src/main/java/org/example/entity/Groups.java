package org.example.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "groups")

public class Groups {
    @Id
    @Column(name = "group_id",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int group_id;
    @Column(name = "name", columnDefinition = "nvarchar(45)")
    private String name;

    public Groups(String name) {
        this.name = name;
    }

    public Groups() {
    }

}
