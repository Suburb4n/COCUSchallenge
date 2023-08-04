package org.example.domain.valueobjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
public class City {
    @Getter
    @Setter
    private String city;


    public City(String city){
        this.city=city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city1 = (City) o;
        return Objects.equals(city, city1.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city);
    }
}
