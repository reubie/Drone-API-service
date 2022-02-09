package com.example.droneAPIservice.dao;

import com.example.droneAPIservice.utilities.ResourceNotFoundException;

public interface CustomMedRepository {

    public Long checkLoadedMedications(long droneId) throws ResourceNotFoundException;

}
