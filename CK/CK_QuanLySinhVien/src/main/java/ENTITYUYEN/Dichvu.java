package ENTITYUYEN;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "dichvu")
public class Dichvu {
    @Id
    @Column(name = "MaDichVu", nullable = false)
    private String maDichVu;

    @Column(name = "TenDichVu")
    private String tenDichVu;

    @Column(name = "SoLuongTon")
    private Integer soLuongTon;

    @Column(name = "DonViTinh")
    private String donViTinh;

    @Column(name = "DonGia")
    private Integer donGia;

    @Column(name = "AnhMinhHoa")
    private String anhMinhHoa;

    @ManyToMany(mappedBy = "dichvus")
    private Set<Hoadonthanhtoan> hoadonthanhtoans;

}