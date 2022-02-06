package com.example.droneAPIservice.daoImplimentationPackage;

import com.example.droneAPIservice.dao.AppRepository;
import com.example.droneAPIservice.entitypackage.Medication;
import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AppRepositoryImplimentation implements AppRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <E> JPAQuery<E> startJPAQuery(Medication entityPath) {
        return new JPAQuery<E>(entityManager).from((EntityPath<?>) entityPath);
    }

}
