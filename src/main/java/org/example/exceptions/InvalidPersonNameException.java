package org.example.exceptions;


import java.util.List;

public class InvalidPersonNameException extends IllegalArgumentException{

    private List<IllegalArgumentException> exceptions;

    public InvalidPersonNameException(List<IllegalArgumentException> exceptions) {
        this.exceptions = exceptions;
    }

    public List<IllegalArgumentException> getExceptions() {
        return exceptions;
    }
}

