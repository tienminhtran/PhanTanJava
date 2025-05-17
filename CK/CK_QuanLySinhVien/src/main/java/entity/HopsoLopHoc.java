package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hopsolophoc")
public class HopsoLopHoc {
    @Id
    private Long masohoso;

    private String ghichu;

    private LocalDate ngaylap;

    @OneToOne
    @JoinColumn(name = "malop")
    private LopHoc lopHoc;
}
