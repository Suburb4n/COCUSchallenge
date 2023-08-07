package org.example.exceptions;

public class NoNullDatesException extends IllegalArgumentException{

    public NoNullDatesException(){
        super("Please insert dates");
    }
}
