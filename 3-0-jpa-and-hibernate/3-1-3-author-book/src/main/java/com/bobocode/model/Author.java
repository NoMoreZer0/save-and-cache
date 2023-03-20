package com.bobocode.model;

import com.bobocode.util.ExerciseNotCompletedException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * todo:
 * - initialize field {@link Author#books} as new {@link HashSet}
 * - implement a helper {@link Author#addBook(Book)} that establishes a relation on both sides
 * - implement a helper {@link Author#removeBook(Book)} that drops a relation on both sides
 * <p>
 * - configure many-to-many relation between {@link Author} and {@link Book}
 * - configure cascade operations for this relations {@link CascadeType#PERSIST} and {@link CascadeType#MERGE}
 * - configure foreign key column "book_id" references book table
 * - configure foreign key column "author_id" references author table
 */

@Getter
@Setter
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)

    @JoinTable(name = "author_book",
            joinColumns = {
                    @JoinColumn(name = "author_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "book_id", referencedColumnName = "id")
            }
    )
    private Set<Book> books;

    public Author() {
        books = new HashSet<>();
    }
    private void setBooks(Set<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
        book.addAuthor(this);
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.removeAuthor(this);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        return Objects.equals(this.id, ((Author) obj).id);
    }
}
