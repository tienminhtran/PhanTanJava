package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
public class Admin extends User implements Serializable {


    public Admin(String username, String password, String email) {
        super(username, password, email);
    }

    public Admin() {
    }
}
