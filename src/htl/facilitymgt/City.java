package htl.facilitymgt;

import java.util.Objects;

public class City implements Comparable<City> {
    private String name;
    private int zipCode;

    public String getName() {
        return name;
    }

    public int getZipCode() {
        return zipCode;
    }

    public City(int zipCode, String name) {
        this.zipCode = zipCode;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return zipCode == city.zipCode;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(zipCode);
    }

    @Override
    public int compareTo(City o) {
        return Integer.compare(this.zipCode, o.zipCode);
    }

    @Override
    public String toString() {
        return String.format("%d %s",zipCode,name);
    }
}
