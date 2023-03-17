package com.bobocode.dao;

import com.bobocode.model.Company;
import com.bobocode.util.ExerciseNotCompletedException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class CompanyDaoImpl implements CompanyDao {
    private EntityManagerFactory entityManagerFactory;

    public CompanyDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Company findByIdFetchProducts(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Company company = entityManager.find(Company.class, id);
        return company;
    }
}
