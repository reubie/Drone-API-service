package com.example.droneAPIservice.utilities;

public class RequirementNotMetException extends Exception  {
    public RequirementNotMetException() {
        super();
    }

    public RequirementNotMetException(String message) {
        super(message);
    }

}
