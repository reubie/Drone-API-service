package com.example.droneAPIservice.daoImplimentationPackage;

import com.example.droneAPIservice.dao.AppRepository;
import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CustomDroneRepositoryImplimentation implements AppRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <E> JPAQuery<E> startJPAQuery(EntityPath<E> entityPath) {
        return new JPAQuery<E>(entityManager).from(entityPath);
    }

}
