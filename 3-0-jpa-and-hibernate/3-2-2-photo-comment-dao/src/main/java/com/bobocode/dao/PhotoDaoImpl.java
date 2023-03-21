package com.bobocode.dao;

import com.bobocode.model.Photo;
import com.bobocode.model.PhotoComment;
import com.bobocode.util.ExerciseNotCompletedException;

import javax.persistence.*;
import java.util.List;

/**
 * Please note that you should not use auto-commit mode for your implementation.
 */
public class PhotoDaoImpl implements PhotoDao {
    private EntityManagerFactory entityManagerFactory;

    public PhotoDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void save(Photo photo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(photo);
        entityTransaction.commit();
    }

    @Override
    public Photo findById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Photo.class, id);
    }

    @Override
    public List<Photo> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Photo> typedQuery = entityManager.createQuery("SELECT p FROM Photo p", Photo.class);
        return typedQuery.getResultList();
    }

    @Override
    public void remove(Photo photo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(entityManager.merge(photo));
        entityTransaction.commit();
    }

    @Override
    public void addComment(long photoId, String comment) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Photo photo = findById(photoId);
        if (photo != null) {
            PhotoComment photoComment = new PhotoComment();
            photoComment.setText(comment);
            photo.addComment(photoComment);
            entityManager.persist(entityManager.merge(photo));
        }
        entityTransaction.commit();
    }
}
