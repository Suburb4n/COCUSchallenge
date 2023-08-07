package org.example.exceptions;

public class InvalidLastNameException extends IllegalArgumentException{

    public InvalidLastNameException(){
        super("Invalid Last Name");
    }
}
