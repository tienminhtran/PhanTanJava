package entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "product_id")
    private int id;



    @Column(name = "model_year")
    private String modelYear;

    @Column(name = "list_price")
    private double listPrice;

    @Column(name = "product_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
