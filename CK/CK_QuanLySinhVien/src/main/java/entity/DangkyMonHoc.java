package entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dangkymonhoc")
public class DangkyMonHoc {
    private float diem;

    private String ghiChu;


    @Id
    @ManyToOne
    @JoinColumn(name = "mamh")
    private Monhoc monhoc;

    @Id
    @ManyToOne
    @JoinColumn(name = "mssv")
    private SinhVien sinhVien;
}
