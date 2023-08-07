package org.example.exceptions;

public class NullDatesException extends IllegalArgumentException{

    public NullDatesException(){
        super("Please insert dates");
    }
}
