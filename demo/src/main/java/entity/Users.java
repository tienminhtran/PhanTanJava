package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Table(name = "users")
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String username;
    private String password;
    private String email;

    public Users( String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Users() {
    }
}
