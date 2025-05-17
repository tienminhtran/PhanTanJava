package ENTITYUYEN;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "ct_khuyenmai")
public class CtKhuyenmai {
    @Id
    @Column(name = "MaKhuyenMai", nullable = false)
    private String maKhuyenMai;

    @Column(name = "TenKhuyenMai")
    private String tenKhuyenMai;

    @Column(name = "NgayBatDau")
    private Instant ngayBatDau;

    @Column(name = "NgayKetThuc")
    private Instant ngayKetThuc;

    @Column(name = "LuotSuDungConLai")
    private Integer luotSuDungConLai;

    @Column(name = "ChietKhau")
    private Integer chietKhau;


    @ManyToOne
    @JoinColumn(name = "MaHoaDon")
    private Hoadonthanhtoan hoadonthanhtoan;

}