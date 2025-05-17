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
public class ChitiethdDichvuId implements Serializable {
    private static final long serialVersionUID = 9117387193538061936L;
    @Column(name = "MaHoaDon", nullable = false)
    private String maHoaDon;

    @Column(name = "MaDichVu", nullable = false)
    private String maDichVu;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ChitiethdDichvuId entity = (ChitiethdDichvuId) o;
        return Objects.equals(this.maDichVu, entity.maDichVu) &&
                Objects.equals(this.maHoaDon, entity.maHoaDon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maDichVu, maHoaDon);
    }

}