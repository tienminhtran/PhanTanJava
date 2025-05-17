package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Department implements Serializable {
    @Id
    private String id;

    private String name;

    private String Location;

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", Location='" + Location + '\'' +
                '}';
    }

    public Department(String id, String name, String location) {
        this.id = id;
        this.name = name;
        Location = location;
    }
}
