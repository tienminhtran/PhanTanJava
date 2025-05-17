package ENTITYUYEN;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "nhanvien")
public class Nhanvien {
    @Id
    @Column(name = "MaNhanVien", nullable = false)
    private String maNhanVien;

    @Column(name = "HoTen")
    private String hoTen;

    @Column(name = "CCCD")
    private String cccd;

    @Column(name = "SoDienThoai")
    private Integer soDienThoai;

    @Column(name = "NgaySinh")
    private Instant ngaySinh;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "GioiTinh")
    private Integer gioiTinh;

    @Column(name = "ChucVu")
    private String chucVu;

    @Column(name = "TrangThai")
    private String trangThai;

    @Column(name = "AnhDaiDien")
    private String anhDaiDien;

}