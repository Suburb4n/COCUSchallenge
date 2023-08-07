package org.example.exceptions;


public class TripIdAlreadyExistsException extends IllegalArgumentException{

    public TripIdAlreadyExistsException(){
        super("Trip already exists!");
    }
}
