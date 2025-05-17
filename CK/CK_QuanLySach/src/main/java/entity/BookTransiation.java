package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book_translations")
public class BookTransiation extends Book{

    @Column(name = "translate_name")
    private String translateName;

    private String language;
}
