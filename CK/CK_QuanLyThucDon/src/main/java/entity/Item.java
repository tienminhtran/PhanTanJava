package entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "items")
//@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item {
    @Id
    private String id;

    private String name;

    private double price;

    private String description;

    @Column(name = "on_special")
    private boolean onSpecial;


    public Item(String id) {
        this.id = id;
    }

    public Item() {
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", onSpecial=" + onSpecial +
                '}';
    }

}
