package com.bobocode.model;

import com.bobocode.util.ExerciseNotCompletedException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * todo:
 * - initialize field comments
 * - map relation between Photo and PhotoComment on the child side
 * - enable cascade type {@link javax.persistence.CascadeType#ALL} for field {@link Photo#comments}
 * - enable orphan removal
 */
@Getter
@Setter
@Entity
@Table(name = "photo")
public class Photo {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "url", nullable = false, unique = true)
    private String url;

    @Column(name = "description", nullable = false, unique = true)
    private String description;

    @OneToMany(mappedBy = "photo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhotoComment> comments;

    public Photo() {
        comments = new ArrayList<>();
    }

    private void setComments(List<PhotoComment> comments) {
        this.comments = comments;
    }

    public void addComment(PhotoComment comment) {
        comments.add(comment);
        comment.setPhoto(this);
    }

    public void removeComment(PhotoComment comment) {
        comments.remove(comment);
        comment.setPhoto(null);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        return Objects.equals(this.id, ((Photo) obj).id);
    }
}
