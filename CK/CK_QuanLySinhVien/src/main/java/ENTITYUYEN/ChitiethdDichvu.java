package ENTITYUYEN;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "chitiethd_dichvu")
public class ChitiethdDichvu {
    @EmbeddedId
    private ChitiethdDichvuId id;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "ThanhTien")
    private Integer thanhTien;

}