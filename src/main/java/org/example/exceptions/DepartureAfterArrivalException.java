package org.example.exceptions;

public class DepartureAfterArrivalException extends IllegalArgumentException {

    public DepartureAfterArrivalException(){
        super("Please select a departure date before an arrival date.");
    }
}
