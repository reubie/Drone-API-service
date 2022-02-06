package com.example.droneAPIservice.dao;

import com.example.droneAPIservice.entitypackage.Medication;
import com.querydsl.jpa.impl.JPAQuery;

public interface AppRepository {

    <E> JPAQuery<E> startJPAQuery(Medication entityPath);

}

