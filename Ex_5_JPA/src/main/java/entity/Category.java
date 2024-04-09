package entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
@NamedQuery(name="Category.addCategory",
        query="INSERT INTO Category (name, id) VALUES (:name, :id)")
@NamedQuery(name="Category.updateCategory",
        query="UPDATE Category SET name = :name WHERE id = :id")
@NamedQuery(name="Category.deleteCategory",
        query="DELETE FROM Category WHERE id = :id")
@NamedQuery(name="Category.findIdCategory",
        query="SELECT c FROM Category c WHERE c.id = :id")
@NamedQuery(name="Category.getAllCategory",
        query="SELECT c FROM Category c")

public class Category {
    @Id
    @Column(name = "category_id")
    private int id;

    @Column(name = "category_name")
    private String name;
}
