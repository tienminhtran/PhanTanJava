package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@ToString
@Table(name = "users_groups")
@EqualsAndHashCode
@Entity
public class Users_Groups {
    @Id
    @ManyToOne
    @JoinColumn(name="group_id") // join be6n group
    private Groups group;


    @Id
    @ManyToOne
    @JoinColumn(name="user_id") // join be6n group
    private Users users;
    public Users_Groups() {
    }

    public Users_Groups(Groups group, Users users) {
        this.group = group;
        this.users = users;
    }
}
