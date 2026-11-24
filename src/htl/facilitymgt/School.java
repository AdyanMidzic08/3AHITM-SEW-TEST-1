package htl.facilitymgt;

import java.util.Objects;

public class School extends Facility {
    private SchoolType schoolType;

    public SchoolType getSchoolType() {
        return schoolType;
    }

    public School(String name, City city, String street, int houseNumber, String schoolType) {
        super(name, city, street, houseNumber);
        this.schoolType = parseSchoolType(schoolType);
    }

    // Wandelt den Text aus der CSV-Datei in den passenden Enum-Wert um
    private static SchoolType parseSchoolType(String schoolType) {
        switch (schoolType) {
            case "AHS-Langform":
                return SchoolType.AHS;
            case "Kaufmännische Schule":
                return SchoolType.HAK;
            case "Technische Lehranstalt":
                return SchoolType.HTL;
            case "Kolleg":
                return SchoolType.KOLLEG;
            case "Mittelschule":
            case "Neue Mittelschule":
                return SchoolType.MS;
            case "Volksschule":
                return SchoolType.VS;
            default:
                return SchoolType.SONSTIGE;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " - " + schoolType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof School)) return false;
        if (!super.equals(o)) return false;   // gleiche Adresse? (macht Facility)
        School other = (School) o;
        return schoolType == other.schoolType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), schoolType);
    }


}
