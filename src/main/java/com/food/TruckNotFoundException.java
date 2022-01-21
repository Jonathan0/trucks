package com.food;

public class TruckNotFoundException extends RuntimeException{
    TruckNotFoundException(Long id) {
        super("Could not find truck id: " + id);
    }
}
