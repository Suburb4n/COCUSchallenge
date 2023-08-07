package org.example.exceptions;

public class InvalidFirstNameException extends IllegalArgumentException{

    public InvalidFirstNameException(){
        super("Invalid First Name");
    }
}
