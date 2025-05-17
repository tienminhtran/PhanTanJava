package ENTITYUYEN;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "khachhang")
public class Khachhang {
    @Id
    @Column(name = "MaKhachHang", nullable = false)
    private String maKhachHang;

    @Column(name = "TenKhachHang")
    private String tenKhachHang;

    @Column(name = "SoDienThoai")
    private Integer soDienThoai;

    @Column(name = "NamSinh")
    private Integer namSinh;

    @Column(name = "GioiTinh")
    private Integer gioiTinh;

}