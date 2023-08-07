package org.example.exceptions;

public class InvalidTravelDurationException extends IllegalArgumentException {
    
    public InvalidTravelDurationException(IllegalArgumentException e){
        super(e);
    }
}
