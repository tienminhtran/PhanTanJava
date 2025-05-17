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
@Table(name = "chitiethd_phong")
public class ChitiethdPhong {
    @EmbeddedId
    private ChitiethdPhongId id;

    @MapsId("maHoaDon")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "MaHoaDon", nullable = false)
    private Hoadonthanhtoan maHoaDon;

    @MapsId("maPhong")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "MaPhong", nullable = false)
    private Phong maPhong;

    @Column(name = "GioVao")
    private Instant gioVao;

    @Column(name = "GioRa")
    private Instant gioRa;

    @Column(name = "TongGioSuDung")
    private Double tongGioSuDung;

    @Column(name = "ThanhTien")
    private Integer thanhTien;

}