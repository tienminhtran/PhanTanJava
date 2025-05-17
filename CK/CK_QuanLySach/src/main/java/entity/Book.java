package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "books")
@Inheritance(strategy = InheritanceType.JOINED)
public class Book {
    @Id
    private String ISBN;

    private String name;
    @Column(name = "publish_year")
    private int publishYear;

    @Column(name = "num_of_pages")
    private int numOfPages;

    private double price;

    @ElementCollection
    @CollectionTable(name = "books_authors", joinColumns = @JoinColumn(name = "ISBN"))
    @Column(name = "author", nullable = false)
    private Set<String> authors;


    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @OneToMany(mappedBy = "book")
    protected Set<Reviews> reviews;

    public Book(String ISBN, String name, int publishYear, int numOfPages, double price, Set<String> authors, Publisher publisher, Set<Reviews> reviews) {
        this.ISBN = ISBN;
        this.name = name;
        this.publishYear = publishYear;
        this.numOfPages = numOfPages;
        this.price = price;
        this.authors = authors;
        this.publisher = publisher;
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN='" + ISBN + '\'' +
                ", name='" + name + '\'' +
                ", publishYear=" + publishYear +
                ", numOfPages=" + numOfPages +
                ", price=" + price +
                ", authors=" + authors +
                ", publisher=" + publisher +
                '}'
                + super.toString();
    }

    public Book(String ISBN, String name, int publishYear, int numOfPages, double price) {
        this.ISBN = ISBN;
        this.name = name;
        this.publishYear = publishYear;
        this.numOfPages = numOfPages;
        this.price = price;
    }

    public Book(String ISBN, String name, int publishYear, int numOfPages, double price, Set<String> authors, Publisher publisher) {
        this.ISBN = ISBN;
        this.name = name;
        this.publishYear = publishYear;
        this.numOfPages = numOfPages;
        this.price = price;
        this.authors = authors;
        this.publisher = publisher;
    }
}
