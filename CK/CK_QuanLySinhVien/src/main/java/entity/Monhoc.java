package entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "monhoc")
public class Monhoc {
    private int hockygd;

    @Id
    private int mamh;

    private String tenmh;
}
