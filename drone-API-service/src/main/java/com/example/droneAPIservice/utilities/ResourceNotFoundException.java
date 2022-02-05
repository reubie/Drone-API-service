package com.example.droneAPIservice.utilities;

public class ResourceNotFoundException  extends Exception {

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
