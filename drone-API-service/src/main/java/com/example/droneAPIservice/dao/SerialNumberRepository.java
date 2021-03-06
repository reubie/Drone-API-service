package com.example.droneAPIservice.dao;

import com.example.droneAPIservice.entitypackage.SerialNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SerialNumberRepository extends JpaRepository<SerialNumber, Long> {

    @Query("SELECT s FROM SerialNumber s where s.value = ?1")
    public SerialNumber findByValue(String value);

}