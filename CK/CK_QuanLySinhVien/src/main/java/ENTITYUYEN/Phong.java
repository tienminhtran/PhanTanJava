package ENTITYUYEN;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "phong")
public class Phong {
    @Id
    @Column(name = "MaPhong", nullable = false)
    private String maPhong;

    @Column(name = "LoaiPhong")
    private Integer loaiPhong;

    @Column(name = "TinhTrang")
    private Integer tinhTrang;

    @Column(name = "SucChua")
    private Integer sucChua;

    @Column(name = "GiaPhong")
    private Integer giaPhong;

    @ManyToMany(mappedBy = "phongs")
    private Set<Hoadonthanhtoan> hoadonthanhtoans;



}
