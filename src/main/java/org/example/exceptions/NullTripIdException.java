package org.example.exceptions;


public class NullTripIdException extends IllegalArgumentException {

    public NullTripIdException(){
        super("Trip Id cannot be null");
    }
}
