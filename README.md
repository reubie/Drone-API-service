# Drone-API-service

## Introduction

This is a service that Customers can communicate with drones via this REST API-based service.
Data from the data.sql file is used to initialize it.


## Installation / Setup instruction
-Open Terminal {Ctrl+Alt+T}
-git clone https://github.com/reubie/Drone-API-service.git
- Build:
- Run: ./mvnw spring-boot:run
- Test: ./mvnw test

## Technologies Used

- JavaScript
- Spring

## Known Bugs

implimentation of query in the medicine repository

## Documentation:

Content-type: application.json.


1. Find drone by id(GET): localhost:8080/drone/{id}
- update drone (PUT): Request body is given below:
```

    {
    "id": 5,
    "serialNumber": {
        "id": 5,
        "value": "PEGEN-BEU3S-OSO0A-2W1ZX-YRGYK-ULCLG"
    },
    "batteryCapacity": 100,
    "model": {
        "id": 5,
        "value": 500,
        "name": "MIDDLE_WEIGHT"
    },
    "state": "IDLE",
    "medications": []
}

```
- Delete drone (DELETE): localhost:8080/drone/{id}

2.  localhost:8080/drone
- register drone (POST): Request body is given below:
```
    {
    "model": {
        "id": 4,
        "value": 500,
        "name": "HEAVY_WEIGHT"
    }
}
```
- Load Medications from drone(GET): localhost:8080/drone/load?droneId=3&medicationIds=1,2,3. It accepts drone id and list of medication ids
- Get drone from loaded medications: localhost:8080/drone/fromLoadedMedications/{droneId}. It checks loaded medications for a given drone.
- Find available drones(GET): localhost:8080/drone/available/{totalMedicationWeight}. It returns a list of drones that are available, given the total medication weight to be loaded.
- Get Battery level: localhost:8080/batteryLevel/{id}. Get drone battery level

2. localhost:8080/med
- register medication(POST): Request body given:
```
    {
    "name": "paracetamol",
    "weight": 250
}
```
- Upload medication image(PUT): localhost:8080/med/{id}
  Content-type: multipart/form-data. It accepts a request param of type file

- Get Medication (GET): localhost:8080/med/{id}
- Update Medication (PUT): localhost:8080/med. Request body given:
```
    {
    "id": 2,
    "name": "ibuprofen",
    "weight": 70
}
```
- Delete medication (DELETE): localhost:8080/med/{id};

