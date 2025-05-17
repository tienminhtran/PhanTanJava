package ENTITYUYEN;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "hoadonthanhtoan")
public class Hoadonthanhtoan {
    @Id
    @Column(name = "MaHoaDon", nullable = false)
    private String maHoaDon;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "MaKhachHang")
    private Khachhang maKhachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "MaNhanVien")
    private Nhanvien maNhanVien;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JoinColumn(name = "MaKhuyenMai")
//    private CtKhuyenmai maKhuyenMai;

    @Column(name = "NgayLap")
    private Instant ngayLap;

    @Column(name = "TongTien")
    private Integer tongTien;


    @ManyToMany
    @JoinTable(name = "chitiethd_phong",
            joinColumns = @JoinColumn(name = "MaHoaDon"),
            inverseJoinColumns = @JoinColumn(name = "MaPhong"))
    private Set<Phong> phongs;


   @ManyToMany
   @JoinTable(name ="chitiethd_dichvu",
            joinColumns = @JoinColumn(name = "MaHoaDon"),
            inverseJoinColumns = @JoinColumn(name = "MaDichVu"))
    private Set<Dichvu> dichvus;


}