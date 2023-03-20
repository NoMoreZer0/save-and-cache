package com.bobocode.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * todo:
 * - implement equals() and hashCode() based on {@link Book#isbn}
 * - make setter for field {@link Book#authors} private
 * - initialize field {@link Book#authors} as new {@link HashSet}
 * <p>
 * - configure JPA entity
 * - specify table name: "book"
 * - configure auto generated identifier
 * - configure mandatory column "name" for field {@link Book#name}
 * - configure mandatory unique column "isbn" for field {@link Book#isbn}, it is a natural key candidate
 * <p>
 * - configure many-to-many relation as mapped on the {@link Author} side
 */
@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @NaturalId
    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private Set<Author> authors;
    public Book() {
        this.authors = new HashSet<>();
    }

    private void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void removeAuthor(Author author) {
        authors.remove(author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        return Objects.equals(this.isbn, ((Book) obj).isbn);
    }
}
