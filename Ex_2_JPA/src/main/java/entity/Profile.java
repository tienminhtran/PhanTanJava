package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "profiles")
public class Profile {
    @Id
    @OneToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;
    private String avatar;
    private String description;

    public Profile(String avatar, String description) {
        this.avatar = avatar;
        this.description = description;
    }
}
