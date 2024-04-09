package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "brands")
public class Brand {
    @Id
    @Column(name = "brand_id")
    private int brandId;

    @Column(name = "brand_name")
    private String brandName;

}
