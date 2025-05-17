package entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "items_ingredients")
public class ItemIngredient {
    @Id
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Id
    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;


    @Override
    public String toString() {
        return "ItemIngredient{" +
                "item=" + item +
                ", ingredient=" + ingredient +
                '}';
    }
}
