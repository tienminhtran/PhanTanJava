package ENTITYUYEN;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "phieudatphong")
public class Phieudatphong {
    @Id
    @Column(name = "MaPhieuDat", nullable = false)
    private String maPhieuDat;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "MaPhong")
    private Phong maPhong;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "MaKhachHang")
    private Khachhang maKhachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "MaNhanVien")
    private Nhanvien maNhanVien;

    @Column(name = "ThoiGianLap")
    private Instant thoiGianLap;

    @Column(name = "ThoiGianNhan")
    private Instant thoiGianNhan;

    @Column(name = "GhiChu")
    private String ghiChu;

    @Column(name = "TinhTrang")
    private Integer tinhTrang;

}