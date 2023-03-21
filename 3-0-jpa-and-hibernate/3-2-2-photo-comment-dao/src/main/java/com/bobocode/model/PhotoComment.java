package com.bobocode.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * todo:
 * - implement equals and hashCode based on identifier field
 *
 * - configure JPA entity
 * - specify table name: "photo_comment"
 * - configure auto generated identifier
 * - configure not nullable column: text
 *
 * - map relation between Photo and PhotoComment using foreign_key column: "photo_id"
 * - configure relation as mandatory (not optional)
 */
@Getter
@Setter
@Entity
@Table(name = "photo_comment")
public class PhotoComment {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "createdOn")
    private LocalDateTime createdOn;

    @ManyToOne(optional = false)
    @JoinColumn(name = "photo_id", nullable = false)
    private Photo photo;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        return Objects.equals(this.id, ((PhotoComment) obj).getId());
    }
}
