package ENTITYUYEN;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ChitiethdPhongId implements Serializable {
    private static final long serialVersionUID = 4390012675831190110L;
    @Column(name = "MaHoaDon", nullable = false)
    private String maHoaDon;

    @Column(name = "MaPhong", nullable = false)
    private String maPhong;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ChitiethdPhongId entity = (ChitiethdPhongId) o;
        return Objects.equals(this.maPhong, entity.maPhong) &&
                Objects.equals(this.maHoaDon, entity.maHoaDon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maPhong, maHoaDon);
    }

}