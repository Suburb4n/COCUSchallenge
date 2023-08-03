package org.example.domain.valueobjects;

import java.time.LocalDate;

public class Date {

    private final LocalDate startDate;

    private final LocalDate endDate;

    public Date(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
