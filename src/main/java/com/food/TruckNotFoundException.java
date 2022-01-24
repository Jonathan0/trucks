package com.food;

public class TruckNotFoundException extends RuntimeException{
    public TruckNotFoundException(Long id) {
        super("Could not find truck id: " + id);
    }
}
