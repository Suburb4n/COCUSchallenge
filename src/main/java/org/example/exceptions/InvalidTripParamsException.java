package org.example.exceptions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class InvalidTripParamsException extends
        IllegalArgumentException {
   @Getter
    private List<IllegalArgumentException> exceptions;

    public InvalidTripParamsException(List <IllegalArgumentException> exceptions) {
        this.exceptions = new ArrayList<>(exceptions);

    }
}
