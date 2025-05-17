package entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lophoc")
public class LopHoc {
    @Id
    private Long malop;

    private String tenlop;


    @ManyToOne
    @JoinColumn(name = "mssv")
    private SinhVien sinhVien;
}
