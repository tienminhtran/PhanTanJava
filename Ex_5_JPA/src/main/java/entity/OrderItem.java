package entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@EqualsAndHashCode // 2 khóa chính
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    @Column(name = "quantity")
    private int quantity;

    @Column(name = "list_price")
    private double listPrice;

    @Column(name = "discount")
    private double discount;
}
