package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sinhvien")
public class SinhVien {
    private String hoten;
    @Id
    private Long mssv;

    private LocalDate ngaysinh;
}
