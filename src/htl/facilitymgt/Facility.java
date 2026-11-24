package htl.facilitymgt;

import java.util.Objects;

public abstract class Facility {
    private City city;
    private String name;
    private String street;
    private int houseNumber;

    public City getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public Facility(String name, City city, String street, int houseNumber) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    // z.B. "Lentos Kunstmuseum Linz (Ernst-Koref-Promenade 1, 4020 Linz)"
    @Override
    public String toString() {
        return String.format("%s (%s %d, %d %s)",
                name, street, houseNumber, city.getZipCode(), city.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Facility facility = (Facility) o;
        return Objects.equals(street, facility.street);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(street);
    }
}
